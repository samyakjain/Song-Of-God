package song.of.god.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import song.of.god.R;
import song.of.god.application.Base.BaseActivity;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @BindView(R.id.container)
    ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected ViewModel getViewModel() {
        return null;
    }

    @Override
    protected void handleIncomingIntent() {

    }

    @Override
    protected void bindAndSetupUI() {

    }
}
