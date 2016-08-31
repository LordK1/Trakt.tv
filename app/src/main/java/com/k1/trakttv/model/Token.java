package com.k1.trakttv.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by K1 on 8/30/16.
 */
@Parcel
public class Token {

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    @SerializedName("token_type")
    @Expose
    private String tokenType;

    @SerializedName("expires_in")
    @Expose
    private int expiresIn;

    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    @SerializedName("scope")
    @Expose
    private String scope;

    @SerializedName("created_at")
    @Expose
    private String createdIn;

    public Token() {
    }

    @Override
    public String toString() {
        return "Token{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", scope='" + scope + '\'' +
                ", createdIn='" + createdIn + '\'' +
                '}';
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(String createdIn) {
        this.createdIn = createdIn;
    }
}
