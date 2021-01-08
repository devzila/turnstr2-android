package com.wdp.Agora;



import com.wdp.Interface.SuperCastClass;

import java.util.List;

public class LiveBroadcast extends SuperCastClass {
    /**
     * success : true
     * message : Push notifications sent successfully
     * data : {"multicast_id":6936412775791680608,"success":1,"failure":0,"canonical_ids":0,"results":[{"message_id":"1596203331349409"}]}
     */

    private String success;
    private String message;
    private DataBean data;

    public String isSuccess() {
        return success;
    }

    public void setSuccess(String success) {
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
        /**
         * multicast_id : 6936412775791680608
         * success : 1
         * failure : 0
         * canonical_ids : 0
         * results : [{"message_id":"1596203331349409"}]
         */

        private String multicast_id;
        private String success;
        private String failure;
        private String canonical_ids;
        private List<ResultsBean> results;

        public String getMulticast_id() {
            return multicast_id;
        }

        public void setMulticast_id(String multicast_id) {
            this.multicast_id = multicast_id;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getFailure() {
            return failure;
        }

        public void setFailure(String failure) {
            this.failure = failure;
        }

        public String getCanonical_ids() {
            return canonical_ids;
        }

        public void setCanonical_ids(String canonical_ids) {
            this.canonical_ids = canonical_ids;
        }

        public List<ResultsBean> getResults() {
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }

        public static class ResultsBean {
            /**
             * message_id : 1596203331349409
             */

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
