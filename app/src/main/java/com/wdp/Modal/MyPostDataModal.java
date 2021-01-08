package com.wdp.Modal;

import com.wdp.Interface.SuperCastClass;

import java.util.List;

public class MyPostDataModal extends SuperCastClass {

    /**
     * success : true
     * message : null
     * data : {"posts":[{"id":148,"type":"Post","caption":"","status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD","media_profile":[{"id":275,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-11T11:22:01.467Z","updated_at":"2020-12-11T11:35:40.482Z","user":{"id":11,"username":"Smith","name":"Smith123","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-20T06:46:21.350Z","updated_at":"2020-12-28T07:06:24.686Z"},"comment":{"id":78,"comment":"good","likes_count":0,"user":{"id":24,"username":"vivo","name":"priyanka","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/thumb/DeepAR_2020_12_10_06_18_41.jpg?1607669650","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/medium/DeepAR_2020_12_10_06_18_41.jpg?1607669650","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/original/DeepAR_2020_12_10_06_18_41.jpg?1607669650"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/thumb/DeepAR_2020_12_14_07_20_09.jpg?1607954013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/medium/DeepAR_2020_12_14_07_20_09.jpg?1607954013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/original/DeepAR_2020_12_14_07_20_09.jpg?1607954013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/thumb/Screenshot_20201210_112353.jpg?1607585510","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/medium/Screenshot_20201210_112353.jpg?1607585510","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/original/Screenshot_20201210_112353.jpg?1607585510"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/thumb/IMG_20201209_190920.jpg?1607585511","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/medium/IMG_20201209_190920.jpg?1607585511","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/original/IMG_20201209_190920.jpg?1607585511"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-12-09T13:25:39.464Z","updated_at":"2020-12-28T10:21:05.817Z"},"created_at":"2020-12-11T11:35:40.475Z","updated_at":"2020-12-11T11:35:40.475Z"},"likes_count":1,"comment_count":1,"share_count":null,"liked_by_current_user":false,"recent_liked_by":{"id":11,"username":"Smith","name":"Smith123","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-20T06:46:21.350Z","updated_at":"2020-12-28T07:06:24.686Z"}}],"pagination":{"current_page":1,"next_page":null,"previous_page":null,"total_pages":1,"per_page":20,"total_entries":7}}
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
         * posts : [{"id":148,"type":"Post","caption":"","status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD","media_profile":[{"id":275,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-11T11:22:01.467Z","updated_at":"2020-12-11T11:35:40.482Z","user":{"id":11,"username":"Smith","name":"Smith123","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-20T06:46:21.350Z","updated_at":"2020-12-28T07:06:24.686Z"},"comment":{"id":78,"comment":"good","likes_count":0,"user":{"id":24,"username":"vivo","name":"priyanka","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/thumb/DeepAR_2020_12_10_06_18_41.jpg?1607669650","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/medium/DeepAR_2020_12_10_06_18_41.jpg?1607669650","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/original/DeepAR_2020_12_10_06_18_41.jpg?1607669650"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/thumb/DeepAR_2020_12_14_07_20_09.jpg?1607954013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/medium/DeepAR_2020_12_14_07_20_09.jpg?1607954013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/original/DeepAR_2020_12_14_07_20_09.jpg?1607954013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/thumb/Screenshot_20201210_112353.jpg?1607585510","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/medium/Screenshot_20201210_112353.jpg?1607585510","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/original/Screenshot_20201210_112353.jpg?1607585510"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/thumb/IMG_20201209_190920.jpg?1607585511","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/medium/IMG_20201209_190920.jpg?1607585511","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/original/IMG_20201209_190920.jpg?1607585511"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-12-09T13:25:39.464Z","updated_at":"2020-12-28T10:21:05.817Z"},"created_at":"2020-12-11T11:35:40.475Z","updated_at":"2020-12-11T11:35:40.475Z"},"likes_count":1,"comment_count":1,"share_count":null,"liked_by_current_user":false,"recent_liked_by":{"id":11,"username":"Smith","name":"Smith123","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-20T06:46:21.350Z","updated_at":"2020-12-28T07:06:24.686Z"}}]
         * pagination : {"current_page":1,"next_page":null,"previous_page":null,"total_pages":1,"per_page":20,"total_entries":7}
         */

        private PaginationBean pagination;
        private List<PostsBean> posts;

        public PaginationBean getPagination() {
            return pagination;
        }

        public void setPagination(PaginationBean pagination) {
            this.pagination = pagination;
        }

        public List<PostsBean> getPosts() {
            return posts;
        }

        public void setPosts(List<PostsBean> posts) {
            this.posts = posts;
        }

        public static class PaginationBean {
            /**
             * current_page : 1
             * next_page : null
             * previous_page : null
             * total_pages : 1
             * per_page : 20
             * total_entries : 7
             */

            private String current_page;
            private String next_page;
            private String previous_page;
            private String total_pages;
            private String per_page;
            private String total_entries;

            public String getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(String current_page) {
                this.current_page = current_page;
            }

            public String getNext_page() {
                return next_page;
            }

            public void setNext_page(String next_page) {
                this.next_page = next_page;
            }

            public String getPrevious_page() {
                return previous_page;
            }

            public void setPrevious_page(String previous_page) {
                this.previous_page = previous_page;
            }

            public String getTotal_pages() {
                return total_pages;
            }

            public void setTotal_pages(String total_pages) {
                this.total_pages = total_pages;
            }

            public String getPer_page() {
                return per_page;
            }

            public void setPer_page(String per_page) {
                this.per_page = per_page;
            }

            public String getTotal_entries() {
                return total_entries;
            }

            public void setTotal_entries(String total_entries) {
                this.total_entries = total_entries;
            }
        }

        public static class PostsBean {
            /**
             * id : 148
             * type : Post
             * caption : 
             * status : pending
             * thumb_url : https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD
             * media_profile : [{"id":275,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD/resize=width:300,height:300","content_type":"photo"}]
             * created_at : 2020-12-11T11:22:01.467Z
             * updated_at : 2020-12-11T11:35:40.482Z
             * user : {"id":11,"username":"Smith","name":"Smith123","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-20T06:46:21.350Z","updated_at":"2020-12-28T07:06:24.686Z"}
             * comment : {"id":78,"comment":"good","likes_count":0,"user":{"id":24,"username":"vivo","name":"priyanka","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/thumb/DeepAR_2020_12_10_06_18_41.jpg?1607669650","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/medium/DeepAR_2020_12_10_06_18_41.jpg?1607669650","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/original/DeepAR_2020_12_10_06_18_41.jpg?1607669650"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/thumb/DeepAR_2020_12_14_07_20_09.jpg?1607954013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/medium/DeepAR_2020_12_14_07_20_09.jpg?1607954013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/original/DeepAR_2020_12_14_07_20_09.jpg?1607954013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/thumb/Screenshot_20201210_112353.jpg?1607585510","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/medium/Screenshot_20201210_112353.jpg?1607585510","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/original/Screenshot_20201210_112353.jpg?1607585510"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/thumb/IMG_20201209_190920.jpg?1607585511","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/medium/IMG_20201209_190920.jpg?1607585511","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/original/IMG_20201209_190920.jpg?1607585511"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-12-09T13:25:39.464Z","updated_at":"2020-12-28T10:21:05.817Z"},"created_at":"2020-12-11T11:35:40.475Z","updated_at":"2020-12-11T11:35:40.475Z"}
             * likes_count : 1
             * comment_count : 1
             * share_count : null
             * liked_by_current_user : false
             * recent_liked_by : {"id":11,"username":"Smith","name":"Smith123","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-20T06:46:21.350Z","updated_at":"2020-12-28T07:06:24.686Z"}
             */

            private String id;
            private String type;
            private String caption;
            private String status;
            private String thumb_url;
            private String created_at;
            private String updated_at;
            private UserBean user;
            private CommentBean comment;
            private String likes_count;
            private String comment_count;
            private String share_count;
            private String liked_by_current_user;
            private RecentLikedByBean recent_liked_by;
            private List<MediaProfileBean> media_profile;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCaption() {
                return caption;
            }

            public void setCaption(String caption) {
                this.caption = caption;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThumb_url() {
                return thumb_url;
            }

            public void setThumb_url(String thumb_url) {
                this.thumb_url = thumb_url;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public CommentBean getComment() {
                return comment;
            }

            public void setComment(CommentBean comment) {
                this.comment = comment;
            }

            public String getLikes_count() {
                return likes_count;
            }

            public void setLikes_count(String likes_count) {
                this.likes_count = likes_count;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getShare_count() {
                return share_count;
            }

            public void setShare_count(String share_count) {
                this.share_count = share_count;
            }

            public String isLiked_by_current_user() {
                return liked_by_current_user;
            }

            public void setLiked_by_current_user(String liked_by_current_user) {
                this.liked_by_current_user = liked_by_current_user;
            }

            public RecentLikedByBean getRecent_liked_by() {
                return recent_liked_by;
            }

            public void setRecent_liked_by(RecentLikedByBean recent_liked_by) {
                this.recent_liked_by = recent_liked_by;
            }

            public List<MediaProfileBean> getMedia_profile() {
                return media_profile;
            }

            public void setMedia_profile(List<MediaProfileBean> media_profile) {
                this.media_profile = media_profile;
            }

            public static class UserBean {
                /**
                 * id : 11
                 * username : Smith
                 * name : Smith123
                 * active : true
                 * avatar : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar2 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar3 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar4 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar5 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar6 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * background : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"}
                 * followed_by_current_user : false
                 * is_broadcasting : false
                 * is_private : false
                 * verified : false
                 * created_at : 2020-11-20T06:46:21.350Z
                 * updated_at : 2020-12-28T07:06:24.686Z
                 */

                private String id;
                private String username;
                private String name;
                private String active;
                private AvatarBean avatar;
                private Avatar2Bean avatar2;
                private Avatar3Bean avatar3;
                private Avatar4Bean avatar4;
                private Avatar5Bean avatar5;
                private Avatar6Bean avatar6;
                private BackgroundBean background;
                private String followed_by_current_user;
                private String is_broadcasting;
                private String is_private;
                private String verified;
                private String created_at;
                private String updated_at;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String isActive() {
                    return active;
                }

                public void setActive(String active) {
                    this.active = active;
                }

                public AvatarBean getAvatar() {
                    return avatar;
                }

                public void setAvatar(AvatarBean avatar) {
                    this.avatar = avatar;
                }

                public Avatar2Bean getAvatar2() {
                    return avatar2;
                }

                public void setAvatar2(Avatar2Bean avatar2) {
                    this.avatar2 = avatar2;
                }

                public Avatar3Bean getAvatar3() {
                    return avatar3;
                }

                public void setAvatar3(Avatar3Bean avatar3) {
                    this.avatar3 = avatar3;
                }

                public Avatar4Bean getAvatar4() {
                    return avatar4;
                }

                public void setAvatar4(Avatar4Bean avatar4) {
                    this.avatar4 = avatar4;
                }

                public Avatar5Bean getAvatar5() {
                    return avatar5;
                }

                public void setAvatar5(Avatar5Bean avatar5) {
                    this.avatar5 = avatar5;
                }

                public Avatar6Bean getAvatar6() {
                    return avatar6;
                }

                public void setAvatar6(Avatar6Bean avatar6) {
                    this.avatar6 = avatar6;
                }

                public BackgroundBean getBackground() {
                    return background;
                }

                public void setBackground(BackgroundBean background) {
                    this.background = background;
                }

                public String isFollowed_by_current_user() {
                    return followed_by_current_user;
                }

                public void setFollowed_by_current_user(String followed_by_current_user) {
                    this.followed_by_current_user = followed_by_current_user;
                }

                public String isIs_broadcasting() {
                    return is_broadcasting;
                }

                public void setIs_broadcasting(String is_broadcasting) {
                    this.is_broadcasting = is_broadcasting;
                }

                public String isIs_private() {
                    return is_private;
                }

                public void setIs_private(String is_private) {
                    this.is_private = is_private;
                }

                public String isVerified() {
                    return verified;
                }

                public void setVerified(String verified) {
                    this.verified = verified;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public static class AvatarBean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar2Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar3Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar4Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar5Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar6Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class BackgroundBean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }
            }

            public static class CommentBean {
                /**
                 * id : 78
                 * comment : good
                 * likes_count : 0
                 * user : {"id":24,"username":"vivo","name":"priyanka","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/thumb/DeepAR_2020_12_10_06_18_41.jpg?1607669650","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/medium/DeepAR_2020_12_10_06_18_41.jpg?1607669650","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/original/DeepAR_2020_12_10_06_18_41.jpg?1607669650"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/thumb/DeepAR_2020_12_14_07_20_09.jpg?1607954013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/medium/DeepAR_2020_12_14_07_20_09.jpg?1607954013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/original/DeepAR_2020_12_14_07_20_09.jpg?1607954013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/thumb/Screenshot_20201210_112353.jpg?1607585510","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/medium/Screenshot_20201210_112353.jpg?1607585510","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/original/Screenshot_20201210_112353.jpg?1607585510"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/thumb/IMG_20201209_190920.jpg?1607585511","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/medium/IMG_20201209_190920.jpg?1607585511","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/original/IMG_20201209_190920.jpg?1607585511"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-12-09T13:25:39.464Z","updated_at":"2020-12-28T10:21:05.817Z"}
                 * created_at : 2020-12-11T11:35:40.475Z
                 * updated_at : 2020-12-11T11:35:40.475Z
                 */

                private String id;
                private String comment;
                private String likes_count;
                private UserBean user;
                private String created_at;
                private String updated_at;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public String getLikes_count() {
                    return likes_count;
                }

                public void setLikes_count(String likes_count) {
                    this.likes_count = likes_count;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public static class UserBean {
                    /**
                     * id : 24
                     * username : vivo
                     * name : priyanka
                     * active : true
                     * avatar : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/thumb/DeepAR_2020_12_10_06_18_41.jpg?1607669650","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/medium/DeepAR_2020_12_10_06_18_41.jpg?1607669650","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/original/DeepAR_2020_12_10_06_18_41.jpg?1607669650"}
                     * avatar2 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"}
                     * avatar3 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/thumb/DeepAR_2020_12_14_07_20_09.jpg?1607954013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/medium/DeepAR_2020_12_14_07_20_09.jpg?1607954013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/original/DeepAR_2020_12_14_07_20_09.jpg?1607954013"}
                     * avatar4 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680"}
                     * avatar5 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/thumb/Screenshot_20201210_112353.jpg?1607585510","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/medium/Screenshot_20201210_112353.jpg?1607585510","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/original/Screenshot_20201210_112353.jpg?1607585510"}
                     * avatar6 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/thumb/IMG_20201209_190920.jpg?1607585511","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/medium/IMG_20201209_190920.jpg?1607585511","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/original/IMG_20201209_190920.jpg?1607585511"}
                     * background : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"}
                     * followed_by_current_user : false
                     * is_broadcasting : false
                     * is_private : false
                     * verified : false
                     * created_at : 2020-12-09T13:25:39.464Z
                     * updated_at : 2020-12-28T10:21:05.817Z
                     */

                    private String id;
                    private String username;
                    private String name;
                    private String active;
                    private AvatarBean avatar;
                    private Avatar2Bean avatar2;
                    private Avatar3Bean avatar3;
                    private Avatar4Bean avatar4;
                    private Avatar5Bean avatar5;
                    private Avatar6Bean avatar6;
                    private BackgroundBean background;
                    private String followed_by_current_user;
                    private String is_broadcasting;
                    private String is_private;
                    private String verified;
                    private String created_at;
                    private String updated_at;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getUsername() {
                        return username;
                    }

                    public void setUsername(String username) {
                        this.username = username;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String isActive() {
                        return active;
                    }

                    public void setActive(String active) {
                        this.active = active;
                    }

                    public AvatarBean getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(AvatarBean avatar) {
                        this.avatar = avatar;
                    }

                    public Avatar2Bean getAvatar2() {
                        return avatar2;
                    }

                    public void setAvatar2(Avatar2Bean avatar2) {
                        this.avatar2 = avatar2;
                    }

                    public Avatar3Bean getAvatar3() {
                        return avatar3;
                    }

                    public void setAvatar3(Avatar3Bean avatar3) {
                        this.avatar3 = avatar3;
                    }

                    public Avatar4Bean getAvatar4() {
                        return avatar4;
                    }

                    public void setAvatar4(Avatar4Bean avatar4) {
                        this.avatar4 = avatar4;
                    }

                    public Avatar5Bean getAvatar5() {
                        return avatar5;
                    }

                    public void setAvatar5(Avatar5Bean avatar5) {
                        this.avatar5 = avatar5;
                    }

                    public Avatar6Bean getAvatar6() {
                        return avatar6;
                    }

                    public void setAvatar6(Avatar6Bean avatar6) {
                        this.avatar6 = avatar6;
                    }

                    public BackgroundBean getBackground() {
                        return background;
                    }

                    public void setBackground(BackgroundBean background) {
                        this.background = background;
                    }

                    public String isFollowed_by_current_user() {
                        return followed_by_current_user;
                    }

                    public void setFollowed_by_current_user(String followed_by_current_user) {
                        this.followed_by_current_user = followed_by_current_user;
                    }

                    public String isIs_broadcasting() {
                        return is_broadcasting;
                    }

                    public void setIs_broadcasting(String is_broadcasting) {
                        this.is_broadcasting = is_broadcasting;
                    }

                    public String isIs_private() {
                        return is_private;
                    }

                    public void setIs_private(String is_private) {
                        this.is_private = is_private;
                    }

                    public String isVerified() {
                        return verified;
                    }

                    public void setVerified(String verified) {
                        this.verified = verified;
                    }

                    public String getCreated_at() {
                        return created_at;
                    }

                    public void setCreated_at(String created_at) {
                        this.created_at = created_at;
                    }

                    public String getUpdated_at() {
                        return updated_at;
                    }

                    public void setUpdated_at(String updated_at) {
                        this.updated_at = updated_at;
                    }

                    public static class AvatarBean {
                        /**
                         * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/thumb/DeepAR_2020_12_10_06_18_41.jpg?1607669650
                         * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/medium/DeepAR_2020_12_10_06_18_41.jpg?1607669650
                         * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/024/original/DeepAR_2020_12_10_06_18_41.jpg?1607669650
                         */

                        private String thumb;
                        private String medium;
                        private String original;

                        public String getThumb() {
                            return thumb;
                        }

                        public void setThumb(String thumb) {
                            this.thumb = thumb;
                        }

                        public String getMedium() {
                            return medium;
                        }

                        public void setMedium(String medium) {
                            this.medium = medium;
                        }

                        public String getOriginal() {
                            return original;
                        }

                        public void setOriginal(String original) {
                            this.original = original;
                        }
                    }

                    public static class Avatar2Bean {
                        /**
                         * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680
                         * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680
                         * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680
                         */

                        private String thumb;
                        private String medium;
                        private String original;

                        public String getThumb() {
                            return thumb;
                        }

                        public void setThumb(String thumb) {
                            this.thumb = thumb;
                        }

                        public String getMedium() {
                            return medium;
                        }

                        public void setMedium(String medium) {
                            this.medium = medium;
                        }

                        public String getOriginal() {
                            return original;
                        }

                        public void setOriginal(String original) {
                            this.original = original;
                        }
                    }

                    public static class Avatar3Bean {
                        /**
                         * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/thumb/DeepAR_2020_12_14_07_20_09.jpg?1607954013
                         * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/medium/DeepAR_2020_12_14_07_20_09.jpg?1607954013
                         * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/024/original/DeepAR_2020_12_14_07_20_09.jpg?1607954013
                         */

                        private String thumb;
                        private String medium;
                        private String original;

                        public String getThumb() {
                            return thumb;
                        }

                        public void setThumb(String thumb) {
                            this.thumb = thumb;
                        }

                        public String getMedium() {
                            return medium;
                        }

                        public void setMedium(String medium) {
                            this.medium = medium;
                        }

                        public String getOriginal() {
                            return original;
                        }

                        public void setOriginal(String original) {
                            this.original = original;
                        }
                    }

                    public static class Avatar4Bean {
                        /**
                         * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/thumb/DeepAR_2020_12_12_12_45_54.jpg?1607943680
                         * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/medium/DeepAR_2020_12_12_12_45_54.jpg?1607943680
                         * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/024/original/DeepAR_2020_12_12_12_45_54.jpg?1607943680
                         */

                        private String thumb;
                        private String medium;
                        private String original;

                        public String getThumb() {
                            return thumb;
                        }

                        public void setThumb(String thumb) {
                            this.thumb = thumb;
                        }

                        public String getMedium() {
                            return medium;
                        }

                        public void setMedium(String medium) {
                            this.medium = medium;
                        }

                        public String getOriginal() {
                            return original;
                        }

                        public void setOriginal(String original) {
                            this.original = original;
                        }
                    }

                    public static class Avatar5Bean {
                        /**
                         * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/thumb/Screenshot_20201210_112353.jpg?1607585510
                         * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/medium/Screenshot_20201210_112353.jpg?1607585510
                         * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/024/original/Screenshot_20201210_112353.jpg?1607585510
                         */

                        private String thumb;
                        private String medium;
                        private String original;

                        public String getThumb() {
                            return thumb;
                        }

                        public void setThumb(String thumb) {
                            this.thumb = thumb;
                        }

                        public String getMedium() {
                            return medium;
                        }

                        public void setMedium(String medium) {
                            this.medium = medium;
                        }

                        public String getOriginal() {
                            return original;
                        }

                        public void setOriginal(String original) {
                            this.original = original;
                        }
                    }

                    public static class Avatar6Bean {
                        /**
                         * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/thumb/IMG_20201209_190920.jpg?1607585511
                         * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/medium/IMG_20201209_190920.jpg?1607585511
                         * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/024/original/IMG_20201209_190920.jpg?1607585511
                         */

                        private String thumb;
                        private String medium;
                        private String original;

                        public String getThumb() {
                            return thumb;
                        }

                        public void setThumb(String thumb) {
                            this.thumb = thumb;
                        }

                        public String getMedium() {
                            return medium;
                        }

                        public void setMedium(String medium) {
                            this.medium = medium;
                        }

                        public String getOriginal() {
                            return original;
                        }

                        public void setOriginal(String original) {
                            this.original = original;
                        }
                    }

                    public static class BackgroundBean {
                        /**
                         * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865
                         * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865
                         * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865
                         */

                        private String thumb;
                        private String medium;
                        private String original;

                        public String getThumb() {
                            return thumb;
                        }

                        public void setThumb(String thumb) {
                            this.thumb = thumb;
                        }

                        public String getMedium() {
                            return medium;
                        }

                        public void setMedium(String medium) {
                            this.medium = medium;
                        }

                        public String getOriginal() {
                            return original;
                        }

                        public void setOriginal(String original) {
                            this.original = original;
                        }
                    }
                }
            }

            public static class RecentLikedByBean {
                /**
                 * id : 11
                 * username : Smith
                 * name : Smith123
                 * active : true
                 * avatar : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar2 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar3 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar4 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar5 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * avatar6 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"}
                 * background : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865"}
                 * followed_by_current_user : false
                 * is_broadcasting : false
                 * is_private : false
                 * verified : false
                 * created_at : 2020-11-20T06:46:21.350Z
                 * updated_at : 2020-12-28T07:06:24.686Z
                 */

                private String id;
                private String username;
                private String name;
                private String active;
                private AvatarBean avatar;
                private Avatar2Bean avatar2;
                private Avatar3Bean avatar3;
                private Avatar4Bean avatar4;
                private Avatar5Bean avatar5;
                private Avatar6Bean avatar6;
                private BackgroundBean background;
                private String followed_by_current_user;
                private String is_broadcasting;
                private String is_private;
                private String verified;
                private String created_at;
                private String updated_at;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String isActive() {
                    return active;
                }

                public void setActive(String active) {
                    this.active = active;
                }

                public AvatarBean getAvatar() {
                    return avatar;
                }

                public void setAvatar(AvatarBean avatar) {
                    this.avatar = avatar;
                }

                public Avatar2Bean getAvatar2() {
                    return avatar2;
                }

                public void setAvatar2(Avatar2Bean avatar2) {
                    this.avatar2 = avatar2;
                }

                public Avatar3Bean getAvatar3() {
                    return avatar3;
                }

                public void setAvatar3(Avatar3Bean avatar3) {
                    this.avatar3 = avatar3;
                }

                public Avatar4Bean getAvatar4() {
                    return avatar4;
                }

                public void setAvatar4(Avatar4Bean avatar4) {
                    this.avatar4 = avatar4;
                }

                public Avatar5Bean getAvatar5() {
                    return avatar5;
                }

                public void setAvatar5(Avatar5Bean avatar5) {
                    this.avatar5 = avatar5;
                }

                public Avatar6Bean getAvatar6() {
                    return avatar6;
                }

                public void setAvatar6(Avatar6Bean avatar6) {
                    this.avatar6 = avatar6;
                }

                public BackgroundBean getBackground() {
                    return background;
                }

                public void setBackground(BackgroundBean background) {
                    this.background = background;
                }

                public String isFollowed_by_current_user() {
                    return followed_by_current_user;
                }

                public void setFollowed_by_current_user(String followed_by_current_user) {
                    this.followed_by_current_user = followed_by_current_user;
                }

                public String isIs_broadcasting() {
                    return is_broadcasting;
                }

                public void setIs_broadcasting(String is_broadcasting) {
                    this.is_broadcasting = is_broadcasting;
                }

                public String isIs_private() {
                    return is_private;
                }

                public void setIs_private(String is_private) {
                    this.is_private = is_private;
                }

                public String isVerified() {
                    return verified;
                }

                public void setVerified(String verified) {
                    this.verified = verified;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public static class AvatarBean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar2Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar3Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar4Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar5Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class Avatar6Bean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }

                public static class BackgroundBean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609150865
                     */

                    private String thumb;
                    private String medium;
                    private String original;

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }
            }

            public static class MediaProfileBean {
                /**
                 * id : 275
                 * url : https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD
                 * sequence : 0
                 * thumb_url : https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD/resize=width:100,height:100
                 * medium_url : https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/luxzzsynRFPIE8jFinZD/resize=width:300,height:300
                 * content_type : photo
                 */

                private String id;
                private String url;
                private String sequence;
                private String thumb_url;
                private String medium_url;
                private String content_type;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getSequence() {
                    return sequence;
                }

                public void setSequence(String sequence) {
                    this.sequence = sequence;
                }

                public String getThumb_url() {
                    return thumb_url;
                }

                public void setThumb_url(String thumb_url) {
                    this.thumb_url = thumb_url;
                }

                public String getMedium_url() {
                    return medium_url;
                }

                public void setMedium_url(String medium_url) {
                    this.medium_url = medium_url;
                }

                public String getContent_type() {
                    return content_type;
                }

                public void setContent_type(String content_type) {
                    this.content_type = content_type;
                }
            }
        }
    }
}
