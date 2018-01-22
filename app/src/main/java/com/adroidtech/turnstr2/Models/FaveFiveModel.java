package com.adroidtech.turnstr2.Models;

/**
 * Created by sarbjot.singh on 2/28/2017.
 */

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FaveFiveModel implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("avatar_face1")
    @Expose
    private String avatarFace1;
    @SerializedName("avatar_face2")
    @Expose
    private String avatarFace2;
    @SerializedName("avatar_face3")
    @Expose
    private String avatarFace3;
    @SerializedName("avatar_face4")
    @Expose
    private String avatarFace4;
    @SerializedName("avatar_face5")
    @Expose
    private String avatarFace5;
    @SerializedName("avatar_face6")
    @Expose
    private String avatarFace6;
    @SerializedName("online")
    @Expose
    private Boolean online;
    private final static long serialVersionUID = -6504675219033009398L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarFace1() {
        return avatarFace1;
    }

    public void setAvatarFace1(String avatarFace1) {
        this.avatarFace1 = avatarFace1;
    }

    public String getAvatarFace2() {
        return avatarFace2;
    }

    public void setAvatarFace2(String avatarFace2) {
        this.avatarFace2 = avatarFace2;
    }

    public String getAvatarFace3() {
        return avatarFace3;
    }

    public void setAvatarFace3(String avatarFace3) {
        this.avatarFace3 = avatarFace3;
    }

    public String getAvatarFace4() {
        return avatarFace4;
    }

    public void setAvatarFace4(String avatarFace4) {
        this.avatarFace4 = avatarFace4;
    }

    public String getAvatarFace5() {
        return avatarFace5;
    }

    public void setAvatarFace5(String avatarFace5) {
        this.avatarFace5 = avatarFace5;
    }

    public String getAvatarFace6() {
        return avatarFace6;
    }

    public void setAvatarFace6(String avatarFace6) {
        this.avatarFace6 = avatarFace6;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

}
