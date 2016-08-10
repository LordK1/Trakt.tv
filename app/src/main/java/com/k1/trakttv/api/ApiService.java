package com.k1.trakttv.api;

import com.google.gson.JsonObject;
import com.k1.trakttv.model.DeviceCode;
import com.k1.trakttv.model.Movie;
import com.k1.trakttv.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * to get list of most Popular movies
     *
     * @param pageNo
     * @param limit
     * @return
     */
    @GET("/movies/popular")
    Call<List<Movie>> getPopularMovies(@Query("page") Integer pageNo, @Query("limit") Integer limit);


    /**
     * Search for defined keywords in defined type of Medias
     *
     * @param type
     * @param query
     * @param pageNo
     * @param limit
     * @return
     */
    @GET("/search/{type}")
    Call<List<Result>> search(@Path("type") String type,
                              @Query("query") String query,
                              @Query("page") Integer pageNo,
                              @Query("limit") Integer limit);

    /**
     * To make first step of Authorization process and get device code
     *
     * @param object
     * @return
     */
    @POST("/oauth/device/code")
    Call<DeviceCode> getDeviceCode(@Body JsonObject object);
}
