package com.adroidtech.turnstr2.Models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDetailModel implements Serializable {

    @SerializedName("auth_token")
    @Expose
    private String authToken;
    @SerializedName("user")
    @Expose
    private ViewUserDetailModel user;
    private final static long serialVersionUID = 3637084470700985229L;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public ViewUserDetailModel getUser() {
        return user;
    }

    public void setUser(ViewUserDetailModel user) {
        this.user = user;
    }

}