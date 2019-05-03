package song.of.god.ui.ChapterDetailScreen;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import song.of.god.R;
import song.of.god.application.Base.BaseActivity;
import song.of.god.database.entity.Chapter;
import song.of.god.database.entity.Verse;
import song.of.god.di.ViewModelProviderFactory;
import song.of.god.network.Resource;
import song.of.god.ui.ChapterDetailScreen.ViewPager.VerticlePagerAdapter;

public class ChapterDetailActivity extends BaseActivity {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private ChapterDetailViewModel chapterDetailViewModel;

    private Chapter chapter;

    private VerticlePagerAdapter verticlePagerAdapter;

    @BindView(R.id.verticleViewPager)
    VerticalViewPager verticalViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_detail);
        ButterKnife.bind(this);
        handleIncomingIntent();
        bindAndSetupUI();
    }

    @Override
    protected ChapterDetailViewModel getViewModel() {
        if (chapterDetailViewModel == null) {
            chapterDetailViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ChapterDetailViewModel.class);
        }
        return chapterDetailViewModel;
    }

    public VerticlePagerAdapter getVerticlePagerAdapter() {
        if (verticlePagerAdapter == null) {
            verticlePagerAdapter = new VerticlePagerAdapter(this);
        }
        return verticlePagerAdapter;
    }

    @Override
    protected void handleIncomingIntent() {
        chapter = Objects.requireNonNull(getIntent().getExtras()).getParcelable("chapter");
    }

    @Override
    protected void bindAndSetupUI() {

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.orange));

        verticalViewPager.setAdapter(getVerticlePagerAdapter());
        getViewModel().getChapterList().observe(this, new Observer<Resource<List<Verse>>>() {
            @Override
            public void onChanged(Resource<List<Verse>> listResource) {
                if (listResource.isLoading()) {
                    if (listResource.data != null && !listResource.data.isEmpty()) {
                        setupUI(listResource.data);
                    }
                } else if (listResource.isSuccess()) {
                    setupUI(listResource.data);
                }
            }

            private void setupUI(List<Verse> data) {
                ArrayList<Object> arrayList = new ArrayList<>();
                arrayList.add(chapter);
                arrayList.addAll(data);
                getVerticlePagerAdapter().setListOfItems(arrayList);
                getVerticlePagerAdapter().notifyDataSetChanged();
            }
        });
        getViewModel().fetchAllChapters(chapter.getChapterNumber());
    }

}
