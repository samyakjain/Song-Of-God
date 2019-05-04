package song.of.god.ui.ChapterDetailScreen.ViewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import song.of.god.R;
import song.of.god.database.entity.Chapter;
import song.of.god.database.entity.Verse;

public class VerticlePagerAdapter extends PagerAdapter {

    List<Object> listOfItems;
    Context mContext;
    LayoutInflater mLayoutInflater;
    int chapterNumber;



    public VerticlePagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listOfItems = new ArrayList<>();
    }

    public void setListOfItems(List<Object> listOfItems) {
        this.listOfItems = listOfItems;
    }

    @Override
    public int getCount() {
        return listOfItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView;
        if (position == 0) {
            Chapter chapter = (Chapter) listOfItems.get(position);
            chapterNumber = chapter.getChapterNumber();
            itemView = mLayoutInflater.inflate(R.layout.chapter_detail_card, container, false);
            TextView chapterTitle = itemView.findViewById(R.id.chapterTitle);
            TextView chapterSummary = itemView.findViewById(R.id.chapterSummary);
            TextView chapterDescription = itemView.findViewById(R.id.chapterDescription);

            chapterTitle.setText("अध्याय" + " " + chapter.getChapterNumber() + " - " + chapter.getName());
            chapterSummary.setText(chapter.getNameMeaning());
            chapterDescription.setText(chapter.getChapterSummary());
            container.addView(itemView);
        } else {
            Object o=listOfItems.get(position);
            if(o instanceof Verse) {
                Verse verse = (Verse) listOfItems.get(position);

                itemView = mLayoutInflater.inflate(R.layout.verse_detail_card_layout, container, false);
                TextView verseNumber = itemView.findViewById(R.id.verseNumber);
                TextView verseTitle = itemView.findViewById(R.id.verseTitle);
                TextView verseWordMeaning = itemView.findViewById(R.id.verseWordMeaning);
                TextView verseMeaning = itemView.findViewById(R.id.verseMeaning);

                verseNumber.setText("अध्याय " + chapterNumber + ", श्लोक " + verse.getVerseNumber() + "/" + ("" + (listOfItems.size() - 1)));
                verseTitle.setText(verse.getText());
                verseWordMeaning.setText(verse.getWordMeanings());
                verseMeaning.setText(verse.getMeaning());
                container.addView(itemView);
            }else{
                itemView = mLayoutInflater.inflate(R.layout.ad_card_interstitial_layout, container, false);
                AdView adView=itemView.findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
                container.addView(itemView);
            }
        }
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CardView) object);
    }
}
