package com.wdp.Modal;



import com.wdp.Interface.SuperCastClass;

import java.util.List;

public class FollowingDataModal extends SuperCastClass {


    /**
     * success : true
     * message : null
     * data : {"following":[{"id":1,"username":"blake","name":"Blake Johnston","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/001/thumb/ios_image20200901121552.jpeg?1598987753","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/001/medium/ios_image20200901121552.jpeg?1598987753","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/001/original/ios_image20200901121552.jpeg?1598987753"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T17:56:29.330Z","updated_at":"2020-10-12T08:43:14.658Z"},{"id":2,"username":"ajag","name":"Alain Grange","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T18:36:42.480Z","updated_at":"2020-10-12T07:53:33.943Z"},{"id":3,"username":"DaRockster","name":"Thomas Jones","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/003/thumb/ios_image20200901123656.jpeg?1598989020","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/003/medium/ios_image20200901123656.jpeg?1598989020","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/003/original/ios_image20200901123656.jpeg?1598989020"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":true,"created_at":"2020-09-01T19:35:10.163Z","updated_at":"2020-10-12T07:53:42.605Z"},{"id":5,"username":"TotallyT ","name":"TotallyT ","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/005/thumb/ios_image20200901150445.jpeg?1598990685","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/005/medium/ios_image20200901150445.jpeg?1598990685","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/005/original/ios_image20200901150445.jpeg?1598990685"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T20:02:13.366Z","updated_at":"2020-09-02T01:53:32.719Z"},{"id":6,"username":"nstedman","name":"Nathan Stedman","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/006/thumb/ios_image20200901162433.jpeg?1598991874","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/006/medium/ios_image20200901162433.jpeg?1598991874","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/006/original/ios_image20200901162433.jpeg?1598991874"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T20:22:14.957Z","updated_at":"2020-10-05T11:37:59.344Z"},{"id":7,"username":"GIZMOH","name":"Nick V.","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/007/thumb/ios_image20200901173553.jpeg?1598996154","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/007/medium/ios_image20200901173553.jpeg?1598996154","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/007/original/ios_image20200901173553.jpeg?1598996154"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":true,"created_at":"2020-09-01T20:30:08.466Z","updated_at":"2020-10-12T07:53:59.823Z"},{"id":10,"username":"blackie","name":"Robyn Black","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/010/thumb/ios_image20200902082230.jpeg?1598998952","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/010/medium/ios_image20200902082230.jpeg?1598998952","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/010/original/ios_image20200902082230.jpeg?1598998952"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T22:20:18.589Z","updated_at":"2020-10-07T03:02:17.622Z"},{"id":11,"username":"LeCorey","name":"LeCorey Harvest ","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/011/thumb/ios_image20200901193151.jpeg?1599003112","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/011/medium/ios_image20200901193151.jpeg?1599003112","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/011/original/ios_image20200901193151.jpeg?1599003112"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T22:44:12.788Z","updated_at":"2020-10-07T02:04:06.741Z"},{"id":12,"username":"ken","name":"Ken Meadows ","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/012/thumb/ios_image20200901184811.jpeg?1599000491","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/012/medium/ios_image20200901184811.jpeg?1599000491","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/012/original/ios_image20200901184811.jpeg?1599000491"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T22:44:16.015Z","updated_at":"2020-10-07T02:04:19.582Z"},{"id":14,"username":"ntmteam ","name":"Neil Do","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/014/thumb/ios_image20200901164235.jpeg?1599003755","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/014/medium/ios_image20200901164235.jpeg?1599003755","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/014/original/ios_image20200901164235.jpeg?1599003755"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T23:40:35.614Z","updated_at":"2020-09-02T01:42:07.627Z"},{"id":15,"username":"mavandenbrekel ","name":"Maverick VandenBrekel","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/015/thumb/ios_image20200901164231.jpeg?1599003751","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/015/medium/ios_image20200901164231.jpeg?1599003751","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/015/original/ios_image20200901164231.jpeg?1599003751"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T23:41:04.711Z","updated_at":"2020-09-02T01:42:19.244Z"},{"id":16,"username":"rdmcmillon","name":"Debbie McMillon","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/016/thumb/ios_image20200901170113.jpeg?1599004874","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/016/medium/ios_image20200901170113.jpeg?1599004874","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/016/original/ios_image20200901170113.jpeg?1599004874"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T23:46:23.426Z","updated_at":"2020-10-07T02:04:28.442Z"},{"id":17,"username":"chris","name":"Chris Azura Arena","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/017/thumb/ios_image20200902080012.jpeg?1599004813","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/017/medium/ios_image20200902080012.jpeg?1599004813","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/017/original/ios_image20200902080012.jpeg?1599004813"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T23:53:35.274Z","updated_at":"2020-10-05T11:41:09.717Z"},{"id":20,"username":"frank","name":"Frank Helring ","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-02T02:18:54.937Z","updated_at":"2020-10-03T15:38:21.941Z"},{"id":21,"username":"peter","name":"Peter Parker","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/021/thumb/image5241.jpg?1601642431","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/021/medium/image5241.jpg?1601642431","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/021/original/image5241.jpg?1601642431"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":true,"verified":false,"created_at":"2020-09-02T03:35:11.834Z","updated_at":"2020-10-02T12:40:31.118Z"},{"id":27,"username":"shubham","name":"Shubham Negi","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-02T12:00:54.125Z","updated_at":"2020-10-06T14:34:52.502Z"},{"id":51,"username":"Ava","name":"ava","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-07T05:44:15.314Z","updated_at":"2020-09-19T10:48:31.006Z"},{"id":56,"username":"bill","name":"Bill Scharpf ","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/056/thumb/ios_image20200909181036.jpeg?1599689437","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/056/medium/ios_image20200909181036.jpeg?1599689437","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/056/original/ios_image20200909181036.jpeg?1599689437"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-09T21:54:52.557Z","updated_at":"2020-10-02T07:34:51.744Z"},{"id":66,"username":"Elijah","name":"Elijah","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/066/thumb/ios_image20200910115201.jpeg?1599718868","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/066/medium/ios_image20200910115201.jpeg?1599718868","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/066/original/ios_image20200910115201.jpeg?1599718868"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-10T05:45:37.652Z","updated_at":"2020-10-12T06:47:58.954Z"},{"id":92,"username":"NilayAnand93411738","name":"Nilay Anand","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-15T11:06:13.852Z","updated_at":"2020-09-28T10:17:04.903Z"}],"pagination":{"current_page":1,"next_page":2,"previous_page":null,"total_pages":2,"per_page":20,"total_entries":21}}
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
         * following : [{"id":1,"username":"blake","name":"Blake Johnston","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/001/thumb/ios_image20200901121552.jpeg?1598987753","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/001/medium/ios_image20200901121552.jpeg?1598987753","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/001/original/ios_image20200901121552.jpeg?1598987753"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T17:56:29.330Z","updated_at":"2020-10-12T08:43:14.658Z"},{"id":2,"username":"ajag","name":"Alain Grange","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T18:36:42.480Z","updated_at":"2020-10-12T07:53:33.943Z"},{"id":3,"username":"DaRockster","name":"Thomas Jones","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/003/thumb/ios_image20200901123656.jpeg?1598989020","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/003/medium/ios_image20200901123656.jpeg?1598989020","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/003/original/ios_image20200901123656.jpeg?1598989020"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":true,"created_at":"2020-09-01T19:35:10.163Z","updated_at":"2020-10-12T07:53:42.605Z"},{"id":5,"username":"TotallyT ","name":"TotallyT ","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/005/thumb/ios_image20200901150445.jpeg?1598990685","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/005/medium/ios_image20200901150445.jpeg?1598990685","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/005/original/ios_image20200901150445.jpeg?1598990685"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T20:02:13.366Z","updated_at":"2020-09-02T01:53:32.719Z"},{"id":6,"username":"nstedman","name":"Nathan Stedman","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/006/thumb/ios_image20200901162433.jpeg?1598991874","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/006/medium/ios_image20200901162433.jpeg?1598991874","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/006/original/ios_image20200901162433.jpeg?1598991874"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T20:22:14.957Z","updated_at":"2020-10-05T11:37:59.344Z"},{"id":7,"username":"GIZMOH","name":"Nick V.","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/007/thumb/ios_image20200901173553.jpeg?1598996154","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/007/medium/ios_image20200901173553.jpeg?1598996154","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/007/original/ios_image20200901173553.jpeg?1598996154"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":true,"created_at":"2020-09-01T20:30:08.466Z","updated_at":"2020-10-12T07:53:59.823Z"},{"id":10,"username":"blackie","name":"Robyn Black","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/010/thumb/ios_image20200902082230.jpeg?1598998952","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/010/medium/ios_image20200902082230.jpeg?1598998952","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/010/original/ios_image20200902082230.jpeg?1598998952"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T22:20:18.589Z","updated_at":"2020-10-07T03:02:17.622Z"},{"id":11,"username":"LeCorey","name":"LeCorey Harvest ","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/011/thumb/ios_image20200901193151.jpeg?1599003112","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/011/medium/ios_image20200901193151.jpeg?1599003112","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/011/original/ios_image20200901193151.jpeg?1599003112"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T22:44:12.788Z","updated_at":"2020-10-07T02:04:06.741Z"},{"id":12,"username":"ken","name":"Ken Meadows ","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/012/thumb/ios_image20200901184811.jpeg?1599000491","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/012/medium/ios_image20200901184811.jpeg?1599000491","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/012/original/ios_image20200901184811.jpeg?1599000491"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T22:44:16.015Z","updated_at":"2020-10-07T02:04:19.582Z"},{"id":14,"username":"ntmteam ","name":"Neil Do","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/014/thumb/ios_image20200901164235.jpeg?1599003755","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/014/medium/ios_image20200901164235.jpeg?1599003755","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/014/original/ios_image20200901164235.jpeg?1599003755"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T23:40:35.614Z","updated_at":"2020-09-02T01:42:07.627Z"},{"id":15,"username":"mavandenbrekel ","name":"Maverick VandenBrekel","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/015/thumb/ios_image20200901164231.jpeg?1599003751","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/015/medium/ios_image20200901164231.jpeg?1599003751","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/015/original/ios_image20200901164231.jpeg?1599003751"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T23:41:04.711Z","updated_at":"2020-09-02T01:42:19.244Z"},{"id":16,"username":"rdmcmillon","name":"Debbie McMillon","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/016/thumb/ios_image20200901170113.jpeg?1599004874","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/016/medium/ios_image20200901170113.jpeg?1599004874","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/016/original/ios_image20200901170113.jpeg?1599004874"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T23:46:23.426Z","updated_at":"2020-10-07T02:04:28.442Z"},{"id":17,"username":"chris","name":"Chris Azura Arena","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/017/thumb/ios_image20200902080012.jpeg?1599004813","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/017/medium/ios_image20200902080012.jpeg?1599004813","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/017/original/ios_image20200902080012.jpeg?1599004813"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-01T23:53:35.274Z","updated_at":"2020-10-05T11:41:09.717Z"},{"id":20,"username":"frank","name":"Frank Helring ","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-02T02:18:54.937Z","updated_at":"2020-10-03T15:38:21.941Z"},{"id":21,"username":"peter","name":"Peter Parker","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/021/thumb/image5241.jpg?1601642431","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/021/medium/image5241.jpg?1601642431","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/021/original/image5241.jpg?1601642431"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":true,"verified":false,"created_at":"2020-09-02T03:35:11.834Z","updated_at":"2020-10-02T12:40:31.118Z"},{"id":27,"username":"shubham","name":"Shubham Negi","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-02T12:00:54.125Z","updated_at":"2020-10-06T14:34:52.502Z"},{"id":51,"username":"Ava","name":"ava","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-07T05:44:15.314Z","updated_at":"2020-09-19T10:48:31.006Z"},{"id":56,"username":"bill","name":"Bill Scharpf ","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/056/thumb/ios_image20200909181036.jpeg?1599689437","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/056/medium/ios_image20200909181036.jpeg?1599689437","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/056/original/ios_image20200909181036.jpeg?1599689437"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-09T21:54:52.557Z","updated_at":"2020-10-02T07:34:51.744Z"},{"id":66,"username":"Elijah","name":"Elijah","active":true,"avatar":{"thumb":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/066/thumb/ios_image20200910115201.jpeg?1599718868","medium":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/066/medium/ios_image20200910115201.jpeg?1599718868","original":"https://s3-us-west-1.amazonaws.com/gizmohsocial-staging-s3/users/avatars/000/000/066/original/ios_image20200910115201.jpeg?1599718868"},"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-10T05:45:37.652Z","updated_at":"2020-10-12T06:47:58.954Z"},{"id":92,"username":"NilayAnand93411738","name":"Nilay Anand","active":true,"avatar":null,"followed_by_current_user":true,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-09-15T11:06:13.852Z","updated_at":"2020-09-28T10:17:04.903Z"}]
         * pagination : {"current_page":1,"next_page":2,"previous_page":null,"total_pages":2,"per_page":20,"total_entries":21}
         */

        private PaginationBean pagination;
        private List<FollowingBean> following;

        public PaginationBean getPagination() {
            return pagination;
        }

        public void setPagination(PaginationBean pagination) {
            this.pagination = pagination;
        }

        public List<FollowingBean> getFollowing() {
            return following;
        }

        public void setFollowing(List<FollowingBean> following) {
            this.following = following;
        }

        public static class PaginationBean {
            /**
             * current_page : 1
             * next_page : 2
             * previous_page : null
             * total_pages : 2
             * per_page : 20
             * total_entries : 21
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


    }
}
