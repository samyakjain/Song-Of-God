package song.of.god.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import song.of.god.network.apiservices.ChapterApiService;
import song.of.god.network.apiservices.TokenApiService;

@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    TokenApiService provideTokenApiService(Retrofit retrofit) {
        return retrofit.create(TokenApiService.class);
    }

    @Provides
    @Singleton
    ChapterApiService provideChapterApiService(Retrofit retrofit) {
        return retrofit.create(ChapterApiService.class);
    }

}
