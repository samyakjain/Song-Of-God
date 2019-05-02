package song.of.god.ui.MainScreen;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import song.of.god.R;
import song.of.god.application.Base.BaseActivity;
import song.of.god.database.entity.Chapter;
import song.of.god.di.ViewModelProviderFactory;
import song.of.god.network.Resource;

import static song.of.god.network.Status.LOADING;
import static song.of.god.network.Status.SUCCESS;

public class MainActivity extends BaseActivity {

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    private int revealX;
    private int revealY;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private MainActivityViewModel mainActivityViewModel;

    @BindView(R.id.container)
    ConstraintLayout container;

    @BindView(R.id.mainActivityProgressBar)
    ProgressBar mainActivityProgressBar;

    @BindView(R.id.chaptersRecyclerView)
    RecyclerView chaptersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("श्रीमद्\u200Dभगवद्\u200Dगीता");
        final Intent intent = getIntent();
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) && intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            container.setVisibility(View.INVISIBLE);
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);
            ViewTreeObserver viewTreeObserver = container.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            container.setVisibility(View.VISIBLE);
        }

        bindAndSetupUI();
    }

    protected void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(container.getWidth(), container.getHeight()) * 1.1);
            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(container, x, y, 0, finalRadius);
            circularReveal.setDuration(500);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            // make the view visible and start the animation
            container.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    @Override
    protected MainActivityViewModel getViewModel() {
        if (mainActivityViewModel == null) {
            mainActivityViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MainActivityViewModel.class);
        }
        return mainActivityViewModel;

    }

    @Override
    protected void handleIncomingIntent() {

    }

    @Override
    protected void bindAndSetupUI() {

        ChaptersListAdapter chaptersListAdapter = new ChaptersListAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        chaptersRecyclerView.setLayoutManager(linearLayoutManager);
        chaptersRecyclerView.setAdapter(chaptersListAdapter);

        getViewModel().getChapterList().observe(this, new Observer<Resource<List<Chapter>>>() {
            @Override
            public void onChanged(Resource<List<Chapter>> listResource) {
                if (listResource.status.equals(LOADING)) {
                    if (listResource.data != null && !listResource.data.isEmpty()) {
                        updateUIForDataReceived(listResource.data);
                    } else {
                        mainActivityProgressBar.setVisibility(View.VISIBLE);
                    }
                } else if (listResource.status.equals(SUCCESS)) {
                    updateUIForDataReceived(listResource.data);
                }
            }

            private void updateUIForDataReceived(List<Chapter> data){
                chaptersListAdapter.setChapterList(data);
                chaptersListAdapter.notifyDataSetChanged();
                mainActivityProgressBar.setVisibility(View.GONE);
            }
        });
        getViewModel().fetchAllChapters();
    }


}
