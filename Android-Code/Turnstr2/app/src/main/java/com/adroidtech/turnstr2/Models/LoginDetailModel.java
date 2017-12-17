package com.adroidtech.turnstr2.Models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDetailModel implements Serializable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    private final static long serialVersionUID = 6363912642345588897L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class User implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("username")
        @Expose
        private Object username;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("phone")
        @Expose
        private Object phone;
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
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("bio")
        @Expose
        private String bio;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("post_count")
        @Expose
        private Integer postCount;
        @SerializedName("following_count")
        @Expose
        private Integer followingCount;
        @SerializedName("follower_count")
        @Expose
        private Integer followerCount;
        @SerializedName("family_count")
        @Expose
        private Integer familyCount;
        @SerializedName("info")
        @Expose
        private String info;
        @SerializedName("contact_me")
        @Expose
        private Object contactMe;
        @SerializedName("online")
        @Expose
        private Boolean online;
        @SerializedName("is_verified")
        @Expose
        private Boolean isVerified;
        @SerializedName("live_session")
        @Expose
        private Object liveSession;
        private final static long serialVersionUID = 5222990648328308767L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
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

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
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

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Integer getPostCount() {
            return postCount;
        }

        public void setPostCount(Integer postCount) {
            this.postCount = postCount;
        }

        public Integer getFollowingCount() {
            return followingCount;
        }

        public void setFollowingCount(Integer followingCount) {
            this.followingCount = followingCount;
        }

        public Integer getFollowerCount() {
            return followerCount;
        }

        public void setFollowerCount(Integer followerCount) {
            this.followerCount = followerCount;
        }

        public Integer getFamilyCount() {
            return familyCount;
        }

        public void setFamilyCount(Integer familyCount) {
            this.familyCount = familyCount;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public Object getContactMe() {
            return contactMe;
        }

        public void setContactMe(Object contactMe) {
            this.contactMe = contactMe;
        }

        public Boolean getOnline() {
            return online;
        }

        public void setOnline(Boolean online) {
            this.online = online;
        }

        public Boolean getIsVerified() {
            return isVerified;
        }

        public void setIsVerified(Boolean isVerified) {
            this.isVerified = isVerified;
        }

        public Object getLiveSession() {
            return liveSession;
        }

        public void setLiveSession(Object liveSession) {
            this.liveSession = liveSession;
        }

    }

    public class Data implements Serializable {

        @SerializedName("auth_token")
        @Expose
        private String authToken;
        @SerializedName("user")
        @Expose
        private User user;
        private final static long serialVersionUID = 3637084470700985229L;

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

}