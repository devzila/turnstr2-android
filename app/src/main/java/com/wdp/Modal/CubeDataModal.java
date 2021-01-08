package com.wdp.Modal;

import com.wdp.Interface.SuperCastClass;

public class CubeDataModal extends SuperCastClass {

    String url;
    String type;
    int thumnail;

    public CubeDataModal(String url, String type,int thumnail) {
        this.url = url;
        this.type = type;
        this.thumnail = thumnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getThumnail() {
        return thumnail;
    }

    public void setThumnail(int thumnail) {
        this.thumnail = thumnail;
    }
}
