package com.chinatelecom.di.rest;

/**
 * Created by song on 2017/11/28.
 */
public enum  RestStatus {

    SERVICE_UNAVAILABLE(503);



    private  int status;

    RestStatus(int status){
        this.status=status;
    }
}
