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


    public Ids(int trakt, String slug, String imdb, int tmdb) {
        this.trakt = trakt;
        this.slug = slug;
        this.imdb = imdb;
        this.tmdb = tmdb;
    }

    @Override
    public String toString() {
        return "Ids{" +
                "trakt=" + trakt +
                ", slug='" + slug + '\'' +
                ", imdb='" + imdb + '\'' +
                ", tmdb=" + tmdb +
                '}';
    }

    // GETTERS & SETTERS
    public int getTrakt() {
        return trakt;
    }

    public void setTrakt(int trakt) {
        this.trakt = trakt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public int getTmdb() {
        return tmdb;
    }

    public void setTmdb(int tmdb) {
        this.tmdb = tmdb;
    }
}
