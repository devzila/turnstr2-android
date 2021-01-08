package com.wdp.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class CreateStroyRequest_model {


    @Expose
    @SerializedName("story")
    private Story story;

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public static class Story {
        @Expose
        @SerializedName("medias_attributes")
        private List<Medias_attributes> medias_attributes;

        public List<Medias_attributes> getMedias_attributes() {
            return medias_attributes;
        }

        public void setMedias_attributes(List<Medias_attributes> medias_attributes) {
            this.medias_attributes = medias_attributes;
        }
    }

    public static class Medias_attributes {
        @Expose
        @SerializedName("guid")
        private String guid;
        @Expose
        @SerializedName("media_content_type")
        private String media_content_type;
        @Expose
        @SerializedName("media_url")
        private String media_url;

        @Expose
        @SerializedName("job_id")
        private String job_id;


        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getMedia_content_type() {
            return media_content_type;
        }

        public void setMedia_content_type(String media_content_type) {
            this.media_content_type = media_content_type;
        }

        public String getMedia_url() {
            return media_url;
        }

        public void setMedia_url(String media_url) {
            this.media_url = media_url;
        }
    }
}
