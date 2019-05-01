package song.of.god.di.component;

import android.app.Application;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import song.of.god.application.GitaApplication;
import song.of.god.di.module.ActivityModule;
import song.of.god.di.module.ApiServiceModule;
import song.of.god.di.module.AppModule;
import song.of.god.di.module.DbModule;
import song.of.god.di.module.FragmentModule;
import song.of.god.di.module.ServiceModule;
import song.of.god.di.module.ViewModelModule;


/**
 * https://stackoverflow.com/questions/38979546/how-can-dagger-2-be-used-to-inject-using-multiple-components-into-the-same-objec
 * for multi-component architecture(either 2 component or component/subcomponent set)
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        DbModule.class,
        ActivityModule.class,
        FragmentModule.class,
        ApiServiceModule.class,
        ViewModelModule.class,
        ServiceModule.class
})
public interface AppComponent {

    void inject(GitaApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);
        AppComponent build();

    }

}
