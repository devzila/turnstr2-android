package com.adroidtech.turnstr2.chat.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by narinder on 18/12/17.
 */

public class Member implements Serializable {


    String id;// 1
    String username;// "NilayDev"
    String first_name;// "Deep"
    String last_name;// "Kumari"
    String avatar_face1;// "https://s3-us-west-2.amazonaws.com/turnstr2-staging/users/avatar_face1s/000/000/001/thumb/user_avatar_face1_?1502471291"
    String avatar_face2;// "https://s3-us-west-2.amazonaws.com/turnstr2-staging/users/avatar_face2s/000/000/001/thumb/user_avatar_face2_?1502471291"
    String avatar_face3;// "https://s3-us-west-2.amazonaws.com/turnstr2-staging/users/avatar_face3s/000/000/001/thumb/user_avatar_face3_?1502471291"
    String avatar_face4;// "https://s3-us-west-2.amazonaws.com/turnstr2-staging/users/avatar_face4s/000/000/001/thumb/user_avatar_face4_?1502471292"
    String avatar_face5;// "https://s3-us-west-2.amazonaws.com/turnstr2-staging/users/avatar_face5s/000/000/001/thumb/user_avatar_face5_?1502471292"
    String avatar_face6;// "https://s3-us-west-2.amazonaws.com/turnstr2-staging/users/avatar_face6s/000/000/001/thumb/user_avatar_face6_?1502471293"
    boolean online;// false
    boolean is_verified;// true

    boolean success;// : true
    String message;
    int total_pages;// : 4
    String current_page;// : 1
    String next_page;// : 2
    String prev_page;// : null
    boolean first_page;// : true
    boolean last_page;// : false


    List<Member> memberList;

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar_face1() {
        return avatar_face1;
    }

    public void setAvatar_face1(String avatar_face1) {
        this.avatar_face1 = avatar_face1;
    }

    public String getAvatar_face2() {
        return avatar_face2;
    }

    public void setAvatar_face2(String avatar_face2) {
        this.avatar_face2 = avatar_face2;
    }

    public String getAvatar_face3() {
        return avatar_face3;
    }

    public void setAvatar_face3(String avatar_face3) {
        this.avatar_face3 = avatar_face3;
    }

    public String getAvatar_face4() {
        return avatar_face4;
    }

    public void setAvatar_face4(String avatar_face4) {
        this.avatar_face4 = avatar_face4;
    }

    public String getAvatar_face5() {
        return avatar_face5;
    }

    public void setAvatar_face5(String avatar_face5) {
        this.avatar_face5 = avatar_face5;
    }

    public String getAvatar_face6() {
        return avatar_face6;
    }

    public void setAvatar_face6(String avatar_face6) {
        this.avatar_face6 = avatar_face6;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(Boolean is_verified) {
        this.is_verified = is_verified;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

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

    public String getPrev_page() {
        return prev_page;
    }

    public void setPrev_page(String prev_page) {
        this.prev_page = prev_page;
    }

    public boolean isFirst_page() {
        return first_page;
    }

    public void setFirst_page(boolean first_page) {
        this.first_page = first_page;
    }

    public boolean isLast_page() {
        return last_page;
    }

    public void setLast_page(boolean last_page) {
        this.last_page = last_page;
    }
}
