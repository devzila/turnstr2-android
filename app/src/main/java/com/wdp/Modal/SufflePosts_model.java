package com.wdp.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SufflePosts_model {



    public static class Post {
        @Expose
        @SerializedName("sequence")
        private List<Sequence_attributes> medias_attributes;

        public List<Sequence_attributes> getMedias_attributes() {
            return medias_attributes;
        }




        public void setMedias_attributes(List<Sequence_attributes> medias_attributes) {
            this.medias_attributes = medias_attributes;
        }
    }

    public static class Sequence_attributes {
        @Expose
        @SerializedName("id")
        private String id;
        @Expose
        @SerializedName("sequence")
        private String sequence;



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence(String sequence) {
            this.sequence = sequence;
        }
    }
}
