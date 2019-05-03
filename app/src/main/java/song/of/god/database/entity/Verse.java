package song.of.god.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"verseNumber","chapterNumber"})
public class Verse implements Parcelable {

    @NonNull
    @SerializedName("chapter_number")
    @Expose
    private Integer chapterNumber;

    @SerializedName("meaning")
    @Expose
    private String meaning;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("transliteration")
    @Expose
    private String transliteration;


    @NonNull
    @SerializedName("verse_number")
    @Expose
    private String verseNumber;

    @SerializedName("word_meanings")
    @Expose
    private String wordMeanings;

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTransliteration() {
        return transliteration;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

    @NonNull
    public String getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(@NonNull String verseNumber) {
        this.verseNumber = verseNumber;
    }

    public String getWordMeanings() {
        return wordMeanings;
    }

    public void setWordMeanings(String wordMeanings) {
        this.wordMeanings = wordMeanings;
    }


    public Verse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.chapterNumber);
        dest.writeString(this.meaning);
        dest.writeString(this.text);
        dest.writeString(this.transliteration);
        dest.writeString(this.verseNumber);
        dest.writeString(this.wordMeanings);
    }

    protected Verse(Parcel in) {
        this.chapterNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        this.meaning = in.readString();
        this.text = in.readString();
        this.transliteration = in.readString();
        this.verseNumber = in.readString();
        this.wordMeanings = in.readString();
    }

    public static final Creator<Verse> CREATOR = new Creator<Verse>() {
        @Override
        public Verse createFromParcel(Parcel source) {
            return new Verse(source);
        }

        @Override
        public Verse[] newArray(int size) {
            return new Verse[size];
        }
    };
}
