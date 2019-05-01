package song.of.god.ui.SplashScreen;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import song.of.god.R;
import song.of.god.application.Base.BaseActivity;
import song.of.god.database.entity.Token;
import song.of.god.di.ViewModelProviderFactory;
import song.of.god.network.Resource;
import song.of.god.ui.MainActivity;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity{

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bindAndSetupUI();
    }

    @Override
    protected SplashViewModel getViewModel() {
        if (splashViewModel == null) {
            splashViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(SplashViewModel.class);
        }
        return splashViewModel;
    }

    @Override
    protected void handleIncomingIntent() {

    }

    @Override
    protected void bindAndSetupUI() {
        getViewModel().getTokenLiveData().observe(SplashActivity.this, new Observer<Resource<Token>>() {
            @Override
            public void onChanged(Resource<Token> tokenResource) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
        getViewModel().fetchToken();
    }

}
