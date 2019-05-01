package song.of.god.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import song.of.god.ui.MainActivity;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

}
