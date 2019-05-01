package song.of.god.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import song.of.god.di.ViewModelProviderFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

//    @Binds
//    @IntoMap
//    @ViewModelKey(HomeViewModel.class)
//    protected abstract ViewModel homeViewModel(HomeViewModel homeViewModel);


}
