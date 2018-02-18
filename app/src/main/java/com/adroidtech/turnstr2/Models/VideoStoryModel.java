package com.adroidtech.turnstr2.Models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoStoryModel implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("opentok_id")
    @Expose
    private String opentokId;
    @SerializedName("event")
    @Expose
    private String event;
    @SerializedName("opentok_created_at")
    @Expose
    private String opentokCreatedAt;
    @SerializedName("likes_count")
    @Expose
    private Object likesCount;
    @SerializedName("comments_count")
    @Expose
    private Object commentsCount;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    private final static long serialVersionUID = -6672154976540389672L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpentokId() {
        return opentokId;
    }

    public void setOpentokId(String opentokId) {
        this.opentokId = opentokId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getOpentokCreatedAt() {
        return opentokCreatedAt;
    }

    public void setOpentokCreatedAt(String opentokCreatedAt) {
        this.opentokCreatedAt = opentokCreatedAt;
    }

    public Object getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Object likesCount) {
        this.likesCount = likesCount;
    }

    public Object getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Object commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}