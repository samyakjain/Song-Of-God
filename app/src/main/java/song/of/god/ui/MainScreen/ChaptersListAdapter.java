package song.of.god.ui.MainScreen;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class ChaptersListAdapter extends RecyclerView.Adapter<ChaptersListAdapter.ChapterCardViewHolder> {

    private List<Chapter> chapterList;

    public ChaptersListAdapter() {
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    @NonNull
    @Override
    public ChapterCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chapter_card, parent, false);
        return new ChapterCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterCardViewHolder holder, int position) {
        holder.configure(chapterList.get(position),position+1);
    }

    @Override
    public int getItemCount() {
        if (chapterList != null) {
            return chapterList.size();
        }
        return 0;
    }

    static class ChapterCardViewHolder extends RecyclerView.ViewHolder {

        private static String[] colorArray = {"#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#2196F3", "#03A9F4", "#00BCD4", "#009688",
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

        public void configure(Chapter chapter, int position) {
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
        public void chapterCardClickHandler(View v){
            Intent intent=new Intent(v.getContext(), ChapterDetailActivity.class);
            intent.putExtra("chapter",chapter);
            ((BaseActivity)v.getContext()).startActivity(intent);
        }


    }
}
