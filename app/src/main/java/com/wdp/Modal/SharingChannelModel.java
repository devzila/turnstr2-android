package com.wdp.Modal;

import com.wdp.Interface.SuperCastClass;

import java.util.List;

public class SharingChannelModel extends SuperCastClass {

    private Boolean success;
    private String message;
    private DataBean data;

    public Boolean isSuccess() {
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private Long multicast_id;
        private Integer success;
        private Integer failure;
        private Integer canonical_ids;
        private List<ResultsBean> results;

        public Long getMulticast_id() {
            return multicast_id;
        }

        public void setMulticast_id(Long multicast_id) {
            this.multicast_id = multicast_id;
        }

        public Integer getSuccess() {
            return success;
        }

        public void setSuccess(Integer success) {
            this.success = success;
        }

        public Integer getFailure() {
            return failure;
        }

        public void setFailure(Integer failure) {
            this.failure = failure;
        }

        public Integer getCanonical_ids() {
            return canonical_ids;
        }

        public void setCanonical_ids(Integer canonical_ids) {
            this.canonical_ids = canonical_ids;
        }

        public List<ResultsBean> getResults() {
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }

        public static class ResultsBean {

            private String message_id;

            public String getMessage_id() {
                return message_id;
            }

            public void setMessage_id(String message_id) {
                this.message_id = message_id;
            }
        }
    }
}
