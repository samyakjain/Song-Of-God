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

    @SerializedName("chapter_summary")
    @Expose
    private String chapterSummary;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("name_meaning")
    @Expose
    private String nameMeaning;

    @SerializedName("name_translation")
    @Expose
    private String nameTranslation;

    @SerializedName("verses_count")
    @Expose
    private Integer versesCount;

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChapterSummary() {
        return chapterSummary;
    }

    public void setChapterSummary(String chapterSummary) {
        this.chapterSummary = chapterSummary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameMeaning() {
        return nameMeaning;
    }

    public void setNameMeaning(String nameMeaning) {
        this.nameMeaning = nameMeaning;
    }

    public String getNameTranslation() {
        return nameTranslation;
    }

    public void setNameTranslation(String nameTranslation) {
        this.nameTranslation = nameTranslation;
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
        dest.writeString(this.chapterSummary);
        dest.writeString(this.name);
        dest.writeString(this.nameMeaning);
        dest.writeString(this.nameTranslation);
        dest.writeValue(this.versesCount);
    }

    public Chapter() {
    }

    protected Chapter(Parcel in) {
        this.chapterNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        this.chapterSummary = in.readString();
        this.name = in.readString();
        this.nameMeaning = in.readString();
        this.nameTranslation = in.readString();
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
