package song.of.god.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import song.of.god.database.entity.Chapter;

@Dao
public interface ChapterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertChapters(List<Chapter> chapters);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertChapter(Chapter chapter);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateChapter(Chapter chapter);

    @Query("SELECT * FROM `chapter` where chapterNumber = :chapterNumber")
    Chapter getChapterByChapterNumber(Long chapterNumber);

    @Query("SELECT * FROM `chapter`")
    LiveData<List<Chapter>> getAllChapters(Long offset);


}
