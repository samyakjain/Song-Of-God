package song.of.god.ui.SplashScreen;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import song.of.god.application.Base.BaseViewModel;
import song.of.god.database.entity.Token;
import song.of.god.network.Resource;
import timber.log.Timber;

public class SplashViewModel extends BaseViewModel {

    @Inject
    public SplashViewModel() {
    }

    @Inject
    SplashRepository splashRepository;

    private MutableLiveData<Resource<Token>> tokenLiveData = new MutableLiveData<>();

    private SplashRepository getSplashRepository() {
        return splashRepository;
    }

    public MutableLiveData<Resource<Token>> getTokenLiveData() {
        return tokenLiveData;
    }

    public void fetchToken() {
        getSplashRepository().getAuthorizationToken().observeForever(listResource -> {
            getTokenLiveData().postValue(listResource);
            Timber.i("observer called");
        });
    }

}
