package song.of.god.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import song.of.god.application.RequestInterceptor;
import song.of.god.di.DiConstants;
import song.of.god.network.errorhandling.RestErrorInterceptor;
import song.of.god.util.UtilityHelper;

@Module
public class AppModule {

    @Provides
    @Singleton
    Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Named(DiConstants.API_END_POINT)
    String providesBaseUrl() {
        return "https://bhagavadgita.io";
    }

    @Provides
    @Singleton
    RestErrorInterceptor providesRestErrorInterceptor(Application application) {
        return new RestErrorInterceptor(application.getApplicationContext());
    }

    @Provides
    @Singleton
    RequestInterceptor providesRequestInterceptor(Application application) {
        return new RequestInterceptor(application.getApplicationContext());
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(RequestInterceptor requestInterceptor, RestErrorInterceptor restErrorInterceptor) {
        if (UtilityHelper.isDebugMode()) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            return new OkHttpClient.Builder()
                    .addNetworkInterceptor(requestInterceptor)
                    .addInterceptor(restErrorInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build();
        } else {
            return new OkHttpClient.Builder()
                    .addNetworkInterceptor(requestInterceptor)
                    .addInterceptor(restErrorInterceptor)
                    .build();
        }
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient, @Named(DiConstants.API_END_POINT) String BASE_URL) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

}
