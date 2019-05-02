package song.of.god.ui.MainScreen;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import song.of.god.database.dao.ChapterDao;
import song.of.god.database.dao.TokenDao;
import song.of.god.database.entity.Chapter;
import song.of.god.network.NetworkBoundResource;
import song.of.god.network.Resource;
import song.of.god.network.apiservices.ChapterApiService;

public class MainActivityRepository {

    @Inject
    public MainActivityRepository() {
    }

    @Inject
    TokenDao tokenDao;

    @Inject
    ChapterDao chapterDao;

    @Inject
    ChapterApiService chapterApiService;


    public LiveData<Resource<List<Chapter>>> getAllChapters() {
        return new NetworkBoundResource<List<Chapter>, List<Chapter>>() {
            @Override
            protected void saveCallResult(@NonNull List<Chapter> item) {
                chapterDao.insertChapters(item);
            }

            @NonNull
            @Override
            protected LiveData<List<Chapter>> loadFromDb() {
                return chapterDao.getAllChapters();
            }

            @NonNull
            @Override
            protected Call<List<Chapter>> createCall() {
                return chapterApiService.getAllChapters(tokenDao.getLatestToken().getAccessToken(), "hi");
            }
        }.getAsLiveData();
    }


}
