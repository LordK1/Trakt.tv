package com.k1.trakttv.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by K1 on 7/18/16.
 */
public class Poster {

    @SerializedName("full")
    private String full;

    @SerializedName("medium")
    private String medium;

    @SerializedName("thumb")
    private String thumb;

    @Override
    public String toString() {
        return "Poster{" +
                "full='" + full + '\'' +
                ", medium='" + medium + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
