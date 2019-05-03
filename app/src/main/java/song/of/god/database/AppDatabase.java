package song.of.god.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import song.of.god.database.dao.ChapterDao;
import song.of.god.database.dao.TokenDao;
import song.of.god.database.dao.VerseDao;
import song.of.god.database.entity.Chapter;
import song.of.god.database.entity.Token;
import song.of.god.database.entity.Verse;

import static song.of.god.application.AppConstants.DATABASE_NAME;

@Database(entities = {Token.class, Chapter.class,Verse.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TokenDao tokenDao();

    public abstract ChapterDao chapterDao();

    public abstract VerseDao verseDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

}
