package com.k1.trakttv.api;

import com.google.gson.JsonObject;
import com.k1.trakttv.model.DeviceCode;
import com.k1.trakttv.model.Movie;
import com.k1.trakttv.model.Result;
import com.k1.trakttv.model.Token;

import java.util.List;

import okhttp3.ResponseBody;
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


    /**
     * This is Step 01 of Authorization with OAuth2 protocol in Track.tv
     * GET https://api.trakt.tv/oauth/authorize?response_type=code&client_id=&redirect_uri=&state=
     *
     * @return
     */
    @GET("/oauth/authorize")
    Call<ResponseBody> sampleAuth(
            @Query("response_type") String type,
            @Query("client_id") String clientId,
            @Query("redirect_uri") String redirectUri,
            @Query("state") String state
    );


    /**
     * code returned back from Step 01
     * {
     * "code": "fd0847dbb559752d932dd3c1ac34ff98d27b11fe2fea5a864f44740cd7919ad0",
     * "client_id": "9b36d8c0db59eff5038aea7a417d73e69aea75b41aac771816d2ef1b3109cc2f",
     * "client_secret": "d6ea27703957b69939b8104ed4524595e210cd2e79af587744a7eb6e58f5b3d2",
     * "redirect_uri": "urn:ietf:wg:oauth:2.0:oob",
     * "grant_type": "authorization_code"}
     * <p>
     * POST https://api.trakt.tv/oauth/token
     *
     * @return
     */
    @POST("/oauth/token")
    Call<Token> accessToken(@Body JsonObject object);


    /**
     * https://api.trakt.tv/calendars/my/shows/2014-09-01/7
     *
     * @return
     */
    @GET("/calendars/my/shows/{date}/{count}")
    Call<ResponseBody> getMyShows(@Path("date") String dateString, @Path("count") int count);

}
