package com.k1.trakttv.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by K1 on 7/17/16.
 */
public final class ServiceGenerator {
    private static final String BASE_URL = "https://api.trakt.tv";

    private static Retrofit.Builder mRetrofitBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL);

    public static <S> S createService(Class<S> serviceClass) {
        final OkHttpClient build = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request original = chain.request();
                final HttpUrl.Builder urlBuilder = original.url().newBuilder().addQueryParameter("extended","full,images");
                final Request.Builder requestBuilder = original.newBuilder();
                requestBuilder
                        .url(urlBuilder.build())
                        .addHeader("Accept", "application/json")
                        .addHeader("trakt-api-version", "2")
                        .addHeader("trakt-api-key", "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086")
                        .method(original.method(), original.body());

                return chain.proceed(requestBuilder.build());
            }
        }).build();
        final Retrofit retrofit = mRetrofitBuilder.client(build).baseUrl(BASE_URL).build();
        return retrofit.create(serviceClass);
    }


}
