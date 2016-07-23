package com.k1.trakttv.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.icu.text.NumberFormat;
import android.os.Build;

import com.google.gson.annotations.SerializedName;
import com.k1.trakttv.BR;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created by K1 on 7/16/16.
 */
public class Movie extends BaseObservable {

    @Bindable
    @SerializedName("title")
    private String title;

    @SerializedName("ids")
    private Ids ids;

    @SerializedName("year")
    private int year;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("overview")
    private String overview;

    @SerializedName("released")
    private String released;

    @SerializedName("runtime")
    private short runtime;

    @SerializedName("rating")
    private float rating;

    @SerializedName("votes")
    private int votes;

    @SerializedName("updated_at")
    private Date updateDate;

    @SerializedName("language")
    private String language;

    @SerializedName("genres")
    private String[] genres;

    @SerializedName("images")
    private Photos photos;

    // Overridden
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", ids=" + ids +
                ", year=" + year +
                ", tagline='" + tagline + '\'' +
                ", overview='" + overview + '\'' +
                ", released='" + released + '\'' +
                ", runtime=" + runtime +
                ", rating=" + rating +
                ", votes=" + votes +
                ", updateDate=" + updateDate +
                ", language='" + language + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", photos=" + photos +
                '}';
    }

    // Shortcuts

    /**
     * To return back String formatted comma separated {@link #votes}
     * // FIXME: 7/18/16 Find solution for android versions
     *
     * @return
     */

    public String getVotesFormatted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return NumberFormat.getNumberInstance(Locale.US).format(this.votes);
        } else {
            return String.format(Locale.US, "%,d", this.votes);
        }
    }

    // Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.movie);
    }

    public Ids getIds() {
        return ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public short getRuntime() {
        return runtime;
    }

    public void setRuntime(short runtime) {
        this.runtime = runtime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }


}
