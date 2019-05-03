package song.of.god.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import song.of.god.di.ViewModelProviderFactory;
import song.of.god.ui.ChapterDetailScreen.ChapterDetailViewModel;
import song.of.god.ui.MainScreen.MainActivityViewModel;
import song.of.god.ui.SplashScreen.SplashViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    protected abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    protected abstract ViewModel bindMainActivityViewModel(MainActivityViewModel mainActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChapterDetailViewModel.class)
    protected abstract ViewModel bindChapterDetailActivityViewModel(ChapterDetailViewModel chapterDetailViewModel);


}
