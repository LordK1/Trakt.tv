package com.k1.trakttv.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by K1 on 7/16/16.
 */
public class Movie {

    @SerializedName("title")
    private String title;

    @SerializedName("ids")
    private Ids ids;


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", ids=" + ids +
                '}';
    }

    // Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Ids getIds() {
        return ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }
}
