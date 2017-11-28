package com.chinatelecom.di.cluster.block;

import com.chinatelecom.di.rest.RestStatus;

import java.util.EnumSet;

/**
 * Created by song on 2017/11/27.
 */
public class ClusterBlock {

    private int id;

    private String description;

    private boolean retryable;

    private boolean disableStatePersistence=false;

    private  RestStatus status;

    private EnumSet<ClusterBlockLevel> leves;


    public ClusterBlock(){

    }


    public ClusterBlock(int id, String description, boolean retryable, boolean disableStatePersistence, RestStatus status, EnumSet<ClusterBlockLevel> leves) {
        this.id=id;
        this.description=description;
        this.retryable=retryable;
        this.disableStatePersistence=disableStatePersistence;
        this.status=status;
        this.leves=leves;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRetryable() {
        return retryable;
    }

    public void setRetryable(boolean retryable) {
        this.retryable = retryable;
    }

    public boolean isDisableStatePersistence() {
        return disableStatePersistence;
    }

    public void setDisableStatePersistence(boolean disableStatePersistence) {
        this.disableStatePersistence = disableStatePersistence;
    }

    public RestStatus getStatus() {
        return status;
    }

    public void setStatus(RestStatus status) {
        this.status = status;
    }

    public EnumSet<ClusterBlockLevel> getLeves() {
        return leves;
    }

    public void setLeves(EnumSet<ClusterBlockLevel> leves) {
        this.leves = leves;
    }
}
