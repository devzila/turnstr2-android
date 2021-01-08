package com.wdp.Interface;


public interface ApiResponseListner {
    public void onSuccess(String Tag, SuperCastClass superCastClass);
    public void onFailure(String message);
    public void onException(String message);

}
