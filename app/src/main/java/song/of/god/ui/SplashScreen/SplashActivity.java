package song.of.god.ui.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

import javax.inject.Inject;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import song.of.god.R;
import song.of.god.application.Base.BaseActivity;
import song.of.god.database.entity.Token;
import song.of.god.di.ViewModelProviderFactory;
import song.of.god.network.Resource;
import song.of.god.network.Status;
import song.of.god.ui.MainScreen.MainActivity;

public class SplashActivity extends BaseActivity {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private SplashViewModel splashViewModel;

    @BindView(R.id.iconImageView)
    ImageView iconImageView;

    @BindView(R.id.progressCircular)
    ProgressBar progressCircular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
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
                if (tokenResource.status.equals(Status.SUCCESS)) {
                    new Handler().postDelayed(() -> presentMainActivity(), 1500);
                }
            }
        });
        getViewModel().fetchToken();
    }

    public void presentMainActivity() {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, progressCircular, "transition");
        int revealX = (int) (progressCircular.getX() + progressCircular.getWidth() / 2);
        int revealY = (int) (progressCircular.getY() + progressCircular.getHeight() / 2);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        ActivityCompat.startActivity(this, intent, options.toBundle());
        new Handler().postDelayed(this::finish,1000);


    }

}
