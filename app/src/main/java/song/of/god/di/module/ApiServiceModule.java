package song.of.god.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import song.of.god.network.apiservices.TokenApiService;

@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    TokenApiService provideTokenService(Retrofit retrofit) {
        return retrofit.create(TokenApiService.class);
    }

}
