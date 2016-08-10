package com.k1.trakttv.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by K1 on 8/10/16.
 */
public class DeviceCode {

    @SerializedName("device_code")
    @Expose
    private String deviceCode;

    @SerializedName("user_code")
    @Expose
    private String userCode;

    @SerializedName("verification_url")
    @Expose
    private String verificationUrl;

    @SerializedName("expires_in")
    @Expose
    private int expiresIn;

    @SerializedName("interval")
    @Expose
    private int interval;

    @Override
    public String toString() {
        return "DeviceCode{" +
                "deviceCode='" + deviceCode + '\'' +
                ", userCode='" + userCode + '\'' +
                ", verificationUrl='" + verificationUrl + '\'' +
                ", expiresIn=" + expiresIn +
                ", interval=" + interval +
                '}';
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getVerificationUrl() {
        return verificationUrl;
    }

    public void setVerificationUrl(String verificationUrl) {
        this.verificationUrl = verificationUrl;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
