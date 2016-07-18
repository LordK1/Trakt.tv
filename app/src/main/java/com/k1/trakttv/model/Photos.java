package com.k1.trakttv.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by K1 on 7/18/16.
 */
public class Photos {

    @SerializedName("fanart")
    private Fanart fanart;

    @SerializedName("poster")
    private Poster poster;

    @SerializedName("thumb")
    private Thumb thumb;

    @Override
    public String toString() {
        return "Photos{" +
                "fanart=" + fanart +
                ", poster=" + poster +
                ", thumb=" + thumb +
                '}';
    }

    public Fanart getFanart() {
        return fanart;
    }

    public void setFanart(Fanart fanart) {
        this.fanart = fanart;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public Thumb getThumb() {
        return thumb;
    }

    public void setThumb(Thumb thumb) {
        this.thumb = thumb;
    }
}
