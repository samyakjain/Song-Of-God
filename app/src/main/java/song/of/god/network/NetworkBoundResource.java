package song.of.god.network;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import song.of.god.util.UtilityHelper;

public abstract class NetworkBoundResource<ResultType, RequestType> {
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource() {
        //TODO: This needs to be changed according to Application requirement
        result.setValue(Resource.loading(null));
        if (shouldfetchDataFromDbBeforeNetwork()) {
            LiveData<ResultType> dbSource = loadFromDb();
            result.addSource(dbSource, data -> {
                result.removeSource(dbSource);
                if (shouldFetchFromNetwork(data)) {
                    fetchFromNetwork(dbSource);
                } else {
                    result.addSource(dbSource, newData -> result.setValue(Resource.success(newData)));
                }
            });
        } else {
            LiveData<ResultType> tempSource = new MutableLiveData<>();
            fetchFromNetwork(tempSource);
        }
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
        createCall().enqueue(new Callback<RequestType>() {
            @Override
            public void onResponse(Call<RequestType> call, Response<RequestType> response) {
                result.removeSource(dbSource);
                if (response.isSuccessful()) {
                    if (shouldStoreDataInDbAfterNetwork()) {
                        saveResultAndReInit(response.body());
                    } else {
                        LiveData<ResultType> resultType = parseNetworkRespone(response.body());
                        if (resultType != null) {
                            result.addSource(resultType, newData -> result.setValue(Resource.success(newData)));
                        } else {
                            result.setValue(Resource.error(UtilityHelper.isDebugMode() ? "Override parseNetworkRespone()" : "Something went wrong", null));
                        }

                    }
                } else {
                    //TODO: handle this error.
                    result.setValue(Resource.error("Something went Wrong", null));
                }
            }

            @Override
            public void onFailure(Call<RequestType> call, Throwable t) {
                onFetchFailed();
                result.removeSource(dbSource);
                result.addSource(dbSource, newData -> result.setValue(Resource.error(t.getMessage(), newData)));
            }
        });
    }

    @MainThread
    private void saveResultAndReInit(RequestType response) {
        new AsyncTask<Void, Void, Void>() {

            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                result.addSource(loadFromDb(), newData -> result.setValue(Resource.success(newData)));
            }
        }.execute();
    }

    @MainThread
    protected LiveData<ResultType> parseNetworkRespone(RequestType body) {
        return null;
    }

    @MainThread
    protected boolean shouldFetchFromNetwork(@Nullable ResultType data) {
        return true;
    }

    @MainThread
    protected boolean shouldfetchDataFromDbBeforeNetwork() {
        return true;
    }


    @MainThread
    protected boolean shouldStoreDataInDbAfterNetwork() {
        return true;
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @NonNull
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Call<RequestType> createCall();

    @MainThread
    protected void onFetchFailed() {

    }

    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }
}
