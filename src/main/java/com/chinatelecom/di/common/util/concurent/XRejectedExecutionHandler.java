package com.chinatelecom.di.common.util.concurent;

import java.util.concurrent.RejectedExecutionHandler;

/**
 * Created by song on 2017/12/5.
 */
public interface XRejectedExecutionHandler  extends RejectedExecutionHandler{

    /**
     *  The number of rejected executions.
     */
    long reject();
}


