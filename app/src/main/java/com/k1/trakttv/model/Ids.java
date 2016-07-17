package com.k1.trakttv.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by K1 on 7/16/16.
 */
public class Ids {


    @SerializedName("trakt")
    private int trakt;

    @SerializedName("slug")
    private String slug;

    @SerializedName("imdb")
    private String imdb;

    @SerializedName("tmdb")
    private int tmdb;


}
