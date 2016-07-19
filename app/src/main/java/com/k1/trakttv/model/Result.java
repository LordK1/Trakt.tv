package com.k1.trakttv.model;

import com.google.gson.annotations.SerializedName;

/**
 * Search Result
 * Created by K1 on 7/19/16.
 */
public class Result {

    @SerializedName("type")
    private String type;

    @SerializedName("score")
    private Float score;

    @SerializedName("movie")
    private Movie movie;

    public Result(String type, Float score, Movie movie) {
        this.type = type;
        this.score = score;
        this.movie = movie;
    }


    @Override
    public String toString() {
        return "Result{" +
                "type='" + type + '\'' +
                ", score=" + score +
                ", movie=" + movie +
                '}';
    }

    // Getters & Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
