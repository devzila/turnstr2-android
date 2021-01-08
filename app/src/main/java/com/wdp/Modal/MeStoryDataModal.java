package com.wdp.Modal;

import com.wdp.Interface.SuperCastClass;

import java.io.Serializable;
import java.util.List;

public class MeStoryDataModal extends SuperCastClass {
    
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
         * stories : [{"id":59,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q","media_profile":[{"id":116,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T12:52:09.066Z","updated_at":"2020-12-03T12:52:09.076Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":58,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8kgCugL7SLizu50awLET","media_profile":[{"id":115,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8kgCugL7SLizu50awLET","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8kgCugL7SLizu50awLET/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8kgCugL7SLizu50awLET/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T12:52:05.097Z","updated_at":"2020-12-03T12:52:05.115Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":55,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY","media_profile":[{"id":109,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY/resize=width:300,height:300","content_type":"photo"},{"id":110,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/Nu5JSfPkT3ahjGttwrrV","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/Nu5JSfPkT3ahjGttwrrV/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/Nu5JSfPkT3ahjGttwrrV/resize=width:300,height:300","content_type":"photo"},{"id":111,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/6ScsnxxVTl66PRCPPvXL","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/6ScsnxxVTl66PRCPPvXL/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/6ScsnxxVTl66PRCPPvXL/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T12:21:00.629Z","updated_at":"2020-12-03T12:21:00.668Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":54,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY","media_profile":[{"id":107,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY/resize=width:300,height:300","content_type":"photo"},{"id":108,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/Nu5JSfPkT3ahjGttwrrV","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/Nu5JSfPkT3ahjGttwrrV/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/Nu5JSfPkT3ahjGttwrrV/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T12:21:00.583Z","updated_at":"2020-12-03T12:21:00.605Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":53,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY","media_profile":[{"id":106,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/YLHtf42URC6sJVKQGFfY/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T12:21:00.569Z","updated_at":"2020-12-03T12:21:00.585Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":52,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf","media_profile":[{"id":103,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf/resize=width:300,height:300","content_type":"photo"},{"id":104,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/ErZ2oPRTJKntBbqI6K9H","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/ErZ2oPRTJKntBbqI6K9H/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/ErZ2oPRTJKntBbqI6K9H/resize=width:300,height:300","content_type":"photo"},{"id":105,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/amjVlezcRJ6rc9XuGhRp","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/amjVlezcRJ6rc9XuGhRp/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/amjVlezcRJ6rc9XuGhRp/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T12:20:52.992Z","updated_at":"2020-12-03T12:20:53.018Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":51,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf","media_profile":[{"id":101,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf/resize=width:300,height:300","content_type":"photo"},{"id":102,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/ErZ2oPRTJKntBbqI6K9H","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/ErZ2oPRTJKntBbqI6K9H/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/ErZ2oPRTJKntBbqI6K9H/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T12:20:52.890Z","updated_at":"2020-12-03T12:20:52.907Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":50,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf","media_profile":[{"id":100,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8voN783vR66YFbdE9Zyf/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T12:20:52.736Z","updated_at":"2020-12-03T12:20:52.750Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":49,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8XqPUpFhTYl19UMbZ3KA","media_profile":[{"id":99,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8XqPUpFhTYl19UMbZ3KA","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8XqPUpFhTYl19UMbZ3KA/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/8XqPUpFhTYl19UMbZ3KA/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:59:13.894Z","updated_at":"2020-12-03T10:59:13.905Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":48,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/PQUX60XrTQiDwQ764Vr6","media_profile":[{"id":98,"url":"https://cdn.filestackcontent.com/PQUX60XrTQiDwQ764Vr6","sequence":0,"content_type":"video","medium_video_url":"https://cdn.filestackcontent.com/PQUX60XrTQiDwQ764Vr6"}],"created_at":"2020-12-03T10:53:18.748Z","updated_at":"2020-12-03T10:53:18.758Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":47,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/TQkxsJajR2uScPpN9M0R","media_profile":[{"id":97,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/TQkxsJajR2uScPpN9M0R","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/TQkxsJajR2uScPpN9M0R/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/TQkxsJajR2uScPpN9M0R/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:52:37.592Z","updated_at":"2020-12-03T10:52:37.603Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":46,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/4fDBkDGuRuGjA5Yx26qD","media_profile":[{"id":96,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/4fDBkDGuRuGjA5Yx26qD","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/4fDBkDGuRuGjA5Yx26qD/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/4fDBkDGuRuGjA5Yx26qD/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:34:44.603Z","updated_at":"2020-12-03T10:34:44.613Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":45,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/3eDbsN9DTFuWHBPYeStY","media_profile":[{"id":95,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/3eDbsN9DTFuWHBPYeStY","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/3eDbsN9DTFuWHBPYeStY/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/3eDbsN9DTFuWHBPYeStY/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:34:42.671Z","updated_at":"2020-12-03T10:34:42.682Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":44,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/UakKhxpJTqa23lzl4TG3","media_profile":[{"id":94,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/UakKhxpJTqa23lzl4TG3","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/UakKhxpJTqa23lzl4TG3/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/UakKhxpJTqa23lzl4TG3/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:32:02.094Z","updated_at":"2020-12-03T10:32:02.105Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":43,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/K4s6PijMQ8uP8EbhxcTo","media_profile":[{"id":93,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/K4s6PijMQ8uP8EbhxcTo","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/K4s6PijMQ8uP8EbhxcTo/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/K4s6PijMQ8uP8EbhxcTo/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:23:53.626Z","updated_at":"2020-12-03T10:23:53.636Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":42,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/falJFAmEQaygWOeFULWY","media_profile":[{"id":92,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/falJFAmEQaygWOeFULWY","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/falJFAmEQaygWOeFULWY/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/falJFAmEQaygWOeFULWY/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:23:49.904Z","updated_at":"2020-12-03T10:23:49.914Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":41,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/qFzSLw6ATmmMUllRFSta","media_profile":[{"id":91,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/qFzSLw6ATmmMUllRFSta","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/qFzSLw6ATmmMUllRFSta/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/qFzSLw6ATmmMUllRFSta/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:23:44.703Z","updated_at":"2020-12-03T10:23:44.713Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":40,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/tXbiuFfWSXuGd5IFIqYV","media_profile":[{"id":90,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/tXbiuFfWSXuGd5IFIqYV","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/tXbiuFfWSXuGd5IFIqYV/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/tXbiuFfWSXuGd5IFIqYV/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:23:40.566Z","updated_at":"2020-12-03T10:23:40.579Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":39,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/GfsAI1UTvKAkMsY5odGB","media_profile":[{"id":89,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/GfsAI1UTvKAkMsY5odGB","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/GfsAI1UTvKAkMsY5odGB/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/GfsAI1UTvKAkMsY5odGB/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-12-03T10:12:46.931Z","updated_at":"2020-12-03T10:12:46.942Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}},{"id":9,"type":"Story","caption":null,"status":"pending","thumb_url":"https://cdn.filestackcontent.com/a0dKdldUTW2Lpq2nYCjw","media_profile":[{"id":25,"url":"https://cdn.filestackcontent.com/a0dKdldUTW2Lpq2nYCjw","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/a0dKdldUTW2Lpq2nYCjw/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/a0dKdldUTW2Lpq2nYCjw/resize=width:300,height:300","content_type":"photo"},{"id":26,"url":"https://cdn.filestackcontent.com/vEkZxCzSOKUvvhRyXrMs","sequence":1,"thumb_url":"https://cdn.filestackcontent.com/vEkZxCzSOKUvvhRyXrMs/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/vEkZxCzSOKUvvhRyXrMs/resize=width:300,height:300","content_type":"photo"},{"id":27,"url":"https://cdn.filestackcontent.com/INSahQGTG2yA8rFkJJkd","sequence":2,"thumb_url":"https://cdn.filestackcontent.com/INSahQGTG2yA8rFkJJkd/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/INSahQGTG2yA8rFkJJkd/resize=width:300,height:300","content_type":"photo"},{"id":28,"url":"https://cdn.filestackcontent.com/ypmzhvb0SrWbVT1ApSBt","sequence":3,"thumb_url":"https://cdn.filestackcontent.com/ypmzhvb0SrWbVT1ApSBt/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/ypmzhvb0SrWbVT1ApSBt/resize=width:300,height:300","content_type":"photo"}],"created_at":"2020-11-25T05:14:01.679Z","updated_at":"2020-11-25T05:14:01.714Z","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}}]
         * pagination : {"current_page":1,"next_page":null,"previous_page":null,"total_pages":1,"per_page":20,"total_entries":20}
         */

        private PaginationBean pagination;
        private List<StoriesBean> stories;

        public PaginationBean getPagination() {
            return pagination;
        }

        public void setPagination(PaginationBean pagination) {
            this.pagination = pagination;
        }

        public List<StoriesBean> getStories() {
            return stories;
        }

        public void setStories(List<StoriesBean> stories) {
            this.stories = stories;
        }

        public static class PaginationBean {
            /**
             * current_page : 1
             * next_page : null
             * previous_page : null
             * total_pages : 1
             * per_page : 20
             * total_entries : 20
             */

            private String current_page;
            private Object next_page;
            private Object previous_page;
            private String total_pages;
            private String per_page;
            private String total_entries;

            public String getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(String current_page) {
                this.current_page = current_page;
            }

            public Object getNext_page() {
                return next_page;
            }

            public void setNext_page(Object next_page) {
                this.next_page = next_page;
            }

            public Object getPrevious_page() {
                return previous_page;
            }

            public void setPrevious_page(Object previous_page) {
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

        public static class StoriesBean {
            /**
             * id : 59
             * type : Story
             * caption : null
             * status : pending
             * thumb_url : https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q
             * media_profile : [{"id":116,"url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q","sequence":0,"thumb_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q/resize=width:100,height:100","medium_url":"https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q/resize=width:300,height:300","content_type":"photo"}]
             * created_at : 2020-12-03T12:52:09.066Z
             * updated_at : 2020-12-03T12:52:09.076Z
             * user : {"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-10T09:23:13.362Z"}
             */

            private String id;
            private String type;
            private Object caption;
            private String status;
            private String thumb_url;
            private String created_at;
            private String updated_at;
            private UserBean user;
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

            public Object getCaption() {
                return caption;
            }

            public void setCaption(Object caption) {
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

            public List<MediaProfileBean> getMedia_profile() {
                return media_profile;
            }

            public void setMedia_profile(List<MediaProfileBean> media_profile) {
                this.media_profile = media_profile;
            }

            public static class UserBean {
                /**
                 * id : 14
                 * username : vicky123
                 * name : Vicky
                 * active : true
                 * avatar : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956"}
                 * avatar2 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956"}
                 * avatar3 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013"}
                 * avatar4 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156"}
                 * avatar5 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"}
                 * avatar6 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"}
                 * background : null
                 * followed_by_current_user : false
                 * is_broadcasting : false
                 * is_private : false
                 * verified : false
                 * created_at : 2020-11-23T12:47:17.386Z
                 * updated_at : 2020-12-10T09:23:13.362Z
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
                public static class BackgroundBean implements Serializable {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609140852
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609140852
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609140852
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
                public static class AvatarBean {
                    /**
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/Title.jpg?1607584956
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/Title.jpg?1607584956
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/Title.jpg?1607584956
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
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/1604398358984.jpg?1607584956
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/1604398358984.jpg?1607584956
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/1604398358984.jpg?1607584956
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
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/1604398193951.jpg?1607585013
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/1604398193951.jpg?1607585013
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/1604398193951.jpg?1607585013
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
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/1604398358984.jpg?1607585156
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/1604398358984.jpg?1607585156
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/1604398358984.jpg?1607585156
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
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933
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
                     * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934
                     * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934
                     * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934
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
                 * id : 116
                 * url : https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q
                 * sequence : 0
                 * thumb_url : https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q/resize=width:100,height:100
                 * medium_url : https://cdn.filestackcontent.com/resize=align:center,width:400,height:400/U00C06PEQoWSEJKM6B3q/resize=width:300,height:300
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
