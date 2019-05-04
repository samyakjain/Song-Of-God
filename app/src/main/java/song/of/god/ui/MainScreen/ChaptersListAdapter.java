package song.of.god.ui.MainScreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import song.of.god.R;
import song.of.god.application.Base.BaseActivity;
import song.of.god.database.entity.Chapter;
import song.of.god.ui.ChapterDetailScreen.ChapterDetailActivity;

public class ChaptersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> chapterList;

    private static final int CHAPTER_VIEW_TYPE = 0;
    private static final int AD_VIEW_TYPE = 1;

    public InterstitialAd mInterstitialAd;
    public Chapter currentChapterClicked;

    public ChaptersListAdapter(Context context) {
        setupInterstialAd(context);

    }

    private void setupInterstialAd(Context context) {
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-6699925777999310/4950789910");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("28A236962E8CB8C073DAA68C77D81655").build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                openChapterDetailActivity(context, currentChapterClicked);
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

            }
        });
    }

    private void openChapterDetailActivity(Context context, Chapter currentChapterClicked) {
        Intent intent = new Intent(context, ChapterDetailActivity.class);
        intent.putExtra("chapter", currentChapterClicked);
        ((BaseActivity) context).startActivity(intent);
    }

    public void setChapterList(List<Object> chapterList) {
        this.chapterList = chapterList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == CHAPTER_VIEW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chapter_card, parent, false);
            return new ChapterCardViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner_ad, parent, false);
            return new AdCardViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object object = chapterList.get(position);
        if (object instanceof Chapter) {
            ((ChapterCardViewHolder) holder).configure((Chapter) chapterList.get(position));
        } else {
            ((AdCardViewHolder) holder).configure();
        }
    }

    @Override
    public int getItemCount() {
        if (chapterList != null) {
            return chapterList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        Object object = chapterList.get(position);
        if (object instanceof Chapter) {
            return CHAPTER_VIEW_TYPE;
        }
        return AD_VIEW_TYPE;
    }

    public class ChapterCardViewHolder extends RecyclerView.ViewHolder {

        private String[] colorArray = {"#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#2196F3", "#03A9F4", "#00BCD4", "#009688",
                "#4CAF50", "#8BC34A", "#CDDC39", "#FFEB3B", "#FFC107", "#FF9800", "#FF5722", "#795548", "#9E9E9E", "#607D8B"};

        @BindView(R.id.chapterTitle)
        TextView chapterTitle;

        @BindView(R.id.chapterSummary)
        TextView chapterSummary;

        @BindView(R.id.view)
        View view;

        Chapter chapter;

        public ChapterCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void configure(Chapter chapter) {
            int position=chapter.getChapterNumber();
            this.chapter = chapter;
            this.view.setBackgroundColor(getRandomColor());
            this.chapterTitle.setText(position + "." + " " + chapter.getName().replaceAll("^\"|\"$", ""));
            this.chapterSummary.setText(chapter.getNameMeaning().replaceAll("^\"|\"$", ""));
        }

        private int getRandomColor() {
            Random r = new Random();
            return Color.parseColor(colorArray[(r.nextInt((18) + 1))]);
        }

        @OnClick(R.id.chapterCard)
        public void chapterCardClickHandler(View v) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                currentChapterClicked = chapter;
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
                openChapterDetailActivity(v.getContext(), chapter);
            }

        }


    }

    static class AdCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adView)
        AdView mAdView;

        public AdCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void configure() {
            AdRequest adRequest = new AdRequest.Builder().addTestDevice("28A236962E8CB8C073DAA68C77D81655").build();
            mAdView.loadAd(adRequest);
        }
    }
}
