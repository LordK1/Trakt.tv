package com.k1.trakttv.api;

import com.k1.trakttv.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * // Notice: 7/17/16 required headers
 *
 * @Headers({ "Accept: ",
 * "trakt-api-version:2",
 * "trakt-api-key: ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086"})
 * Created by K1 on 7/16/16.
 */

public interface ApiService {

    @GET("/movies/popular")
    Call<List<Movie>> getPopularMovies(@Query("page") Integer pageNo, @Query("limti") Integer limit);


    @GET("/search/{type}")
    Call<List<Movie>> search(@Path("type") String type,
                             @Query("query") String query,
                             @Query("page") Integer pageNo,
                             @Query("limit") Integer limit);
}
