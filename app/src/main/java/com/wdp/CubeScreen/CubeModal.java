package com.wdp.CubeScreen;

public class CubeModal {

    String url;
    int icon;

    public CubeModal(String url, Integer icon) {
        this.url = url;
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }
}
