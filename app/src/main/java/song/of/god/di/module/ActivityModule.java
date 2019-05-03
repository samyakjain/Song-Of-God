package song.of.god.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import song.of.god.ui.ChapterDetailScreen.ChapterDetailActivity;
import song.of.god.ui.MainScreen.MainActivity;
import song.of.god.ui.SplashScreen.SplashActivity;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract ChapterDetailActivity bindChapterDetailActivity();

}
