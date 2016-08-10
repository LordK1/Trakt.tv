package com.k1.trakttv.dependency;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.k1.trakttv.api.ApiService;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Client ID: e50771bf01afe4441d1b32d25c76d3c544de65cc449a16276ed4d05aff168168
 Client Secret: 49bb1f723bd5ab766270edbb6ac43942a7ce6fc37847165af38008565c7cd188

 *
 * Created by K1 on 7/17/16.
 */
@Module
public class AppModule {

    private static final String BASE_URL = "https://api.trakt.tv";
    private static final String DATA_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private final Retrofit retrofit;
    private final OkHttpClient client;
    private final Gson gson;
    private Application application;

    public AppModule(Application application) {
        this.application = application;

        client = new OkHttpClient.Builder()
                .addInterceptor(new CustomInterceptor())
                .build();

        gson = new GsonBuilder().setDateFormat(DATA_FORMAT).create();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
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

    @ApplicationScope
    @Provides
    public Gson provideGson() {
        return gson;
    }

    /**
     * To Add Customized {@link retrofit2.http.Header} into {@link Request}
     * and then added inot {@link OkHttpClient}
     */
    private static class CustomInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request original = chain.request();
            final Request.Builder builder = original.newBuilder();
            final HttpUrl queryUrl = original.url().newBuilder()
                    .addQueryParameter("extended", "full,images")
                    .build();
            builder
                    .url(queryUrl)
                    .addHeader("Accept", "application/json")
                    .addHeader("trakt-api-version", "2")
//                  .addHeader("trakt-api-key", "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086")
                    .addHeader("trakt-api-key", "e50771bf01afe4441d1b32d25c76d3c544de65cc449a16276ed4d05aff168168")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(builder.build());
        }
    }




}
