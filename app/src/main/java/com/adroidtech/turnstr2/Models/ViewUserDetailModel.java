package com.adroidtech.turnstr2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ViewUserDetailModel implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("phone")
    @Expose
    private String phone;
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
    private String contactMe;
    @SerializedName("online")
    @Expose
    private Boolean online;
    @SerializedName("is_verified")
    @Expose
    private Boolean isVerified;
    @SerializedName("following")
    @Expose
    private Boolean following;
    @SerializedName("following_me")
    @Expose
    private Boolean followingMe;
    @SerializedName("family_member")
    @Expose
    private Boolean familyMember;
    @SerializedName("favourite")
    @Expose
    private Boolean favourite;
    @SerializedName("live_session")
    @Expose
    private LiveSession liveSession;
    private final static long serialVersionUID = -2832678596606925708L;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public String getContactMe() {
        return contactMe;
    }

    public void setContactMe(String contactMe) {
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

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public Boolean getFollowingMe() {
        return followingMe;
    }

    public void setFollowingMe(Boolean followingMe) {
        this.followingMe = followingMe;
    }

    public Boolean getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(Boolean familyMember) {
        this.familyMember = familyMember;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public LiveSession getLiveSession() {
        return liveSession;
    }

    public void setLiveSession(LiveSession liveSession) {
        this.liveSession = liveSession;
    }


    public class LiveSession implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("session_id")
        @Expose
        private String sessionId;
        @SerializedName("completed")
        @Expose
        private Boolean completed;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("session_type")
        @Expose
        private String sessionType;
        private final static long serialVersionUID = 5060110103033264091L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public Boolean getCompleted() {
            return completed;
        }

        public void setCompleted(Boolean completed) {
            this.completed = completed;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSessionType() {
            return sessionType;
        }

        public void setSessionType(String sessionType) {
            this.sessionType = sessionType;
        }

    }
}