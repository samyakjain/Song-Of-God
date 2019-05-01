package song.of.god.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;

@Entity(primaryKeys = ("chapterNumber"))
public class Chapter implements Parcelable {

    @SerializedName("chapter_number")
    @Expose
    private Integer chapterNumber;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("name_english")
    @Expose
    private String nameEnglish;

    @SerializedName("name_transliterated")
    @Expose
    private String nameTransliterated;

    @SerializedName("name_transliterated_simple")
    @Expose
    private String nameTransliteratedSimple;

    @SerializedName("verses_count")
    @Expose
    private Integer versesCount;

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getNameTransliterated() {
        return nameTransliterated;
    }

    public void setNameTransliterated(String nameTransliterated) {
        this.nameTransliterated = nameTransliterated;
    }

    public String getNameTransliteratedSimple() {
        return nameTransliteratedSimple;
    }

    public void setNameTransliteratedSimple(String nameTransliteratedSimple) {
        this.nameTransliteratedSimple = nameTransliteratedSimple;
    }

    public Integer getVersesCount() {
        return versesCount;
    }

    public void setVersesCount(Integer versesCount) {
        this.versesCount = versesCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.chapterNumber);
        dest.writeString(this.name);
        dest.writeString(this.nameEnglish);
        dest.writeString(this.nameTransliterated);
        dest.writeString(this.nameTransliteratedSimple);
        dest.writeValue(this.versesCount);
    }

    public Chapter() {
    }

    protected Chapter(Parcel in) {
        this.chapterNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.nameEnglish = in.readString();
        this.nameTransliterated = in.readString();
        this.nameTransliteratedSimple = in.readString();
        this.versesCount = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Chapter> CREATOR = new Creator<Chapter>() {
        @Override
        public Chapter createFromParcel(Parcel source) {
            return new Chapter(source);
        }

        @Override
        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };
}
