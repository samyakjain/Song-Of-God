package song.of.god.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import song.of.god.database.entity.Verse;

@Dao
public interface VerseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertVerses(List<Verse> verses);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertVerse(Verse verse);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateVerse(Verse verse);

    @Query("SELECT * FROM `verse` where verseNumber = :verseNumber")
    Verse getVerseByVerseNumber(Long verseNumber);

    @Query("SELECT * FROM `verse` where chapterNumber =:chapterNumber")
    LiveData<List<Verse>> getAllVersesForAChapter(Integer chapterNumber);
}
