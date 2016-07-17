package com.k1.trakttv.dependency;

import android.app.Application;

import com.k1.trakttv.api.ApiService;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by K1 on 7/17/16.
 */
@Module
public class AppModule {

    private static final String BASE_URL = "https://api.trakt.tv";
    private final Retrofit retrofit;
    private final OkHttpClient client;
    private Application application;

    public AppModule(Application application) {
        this.application = application;

        client = new OkHttpClient.Builder()
                .addInterceptor(new CustomInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    @ApplicationScope // using the previously defined scope, note that @Singleton will not work
    @Provides
    public ApiService provideApiService() {
        return retrofit.create(ApiService.class);
    }

    @ApplicationScope
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return client;
    }

    /**
     * To Add Customized {@link retrofit2.http.Header} into {@link Request}
     * and then added inot {@link OkHttpClient}
     */
    private static class CustomInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request original = chain.request();
            original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("trakt-api-version", "2")
                    .addHeader("trakt-api-key", "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086")
                    .method(original.method(), original.body()).build();
            return chain.proceed(original);
        }
    }


}
