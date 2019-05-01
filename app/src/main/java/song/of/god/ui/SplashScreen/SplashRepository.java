package song.of.god.ui.SplashScreen;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import song.of.god.application.AppConstants;
import song.of.god.database.dao.TokenDao;
import song.of.god.database.entity.Token;
import song.of.god.network.NetworkBoundResource;
import song.of.god.network.Resource;
import song.of.god.network.apiservices.TokenApiService;

public class SplashRepository {

    @Inject
    public SplashRepository() {
    }

    @Inject
    public TokenDao tokenDao;

    @Inject
    public TokenApiService tokenApiService;

    public LiveData<Resource<Token>> getAuthorizationToken() {
        return new NetworkBoundResource<Token, Token>() {
            @Override
            protected void saveCallResult(@NonNull Token item) {
                tokenDao.insertToken(item);
            }

            @NonNull
            @Override
            protected LiveData<Token> loadFromDb() {
                return tokenDao.getLatestTokenAsLiveData();
            }

            @NonNull
            @Override
            protected Call<Token> createCall() {
                return tokenApiService.getToken(AppConstants.ApiClientId, AppConstants.ApiClientSecret, "client_credentials", "verse chapter");
            }

            @Override
            protected boolean shouldfetchDataFromDbBeforeNetwork() {
                return false;
            }

        }.getAsLiveData();
    }

}
