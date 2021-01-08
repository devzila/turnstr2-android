package com.wdp.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wdp.Interface.SuperCastClass;

public  class ForgotpasswordDataModal extends SuperCastClass {


    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("data")
    private String data;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("success")
    private String success;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
