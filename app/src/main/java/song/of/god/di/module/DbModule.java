package song.of.god.di.module;

import android.app.Application;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import song.of.god.database.AppDatabase;
import song.of.god.database.dao.TokenDao;

import static song.of.god.application.AppConstants.DATABASE_NAME;


@Module
public class DbModule {

    @Provides
    @Singleton
    AppDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    TokenDao provideAudioDao(@NonNull AppDatabase appDatabase) {
        return appDatabase.tokenDao();
    }

}
