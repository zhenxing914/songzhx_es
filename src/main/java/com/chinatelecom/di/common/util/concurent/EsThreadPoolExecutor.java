package com.chinatelecom.di.common.util.concurent;

import java.util.concurrent.*;

/**
 * Created by song on 2017/12/1.
 */
public class EsThreadPoolExecutor  extends ThreadPoolExecutor{

    /**
     * Name used in error report
     */
    private final String name;

    public EsThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, PriorityBlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,XRejectedExecutionHandler handler) {

        super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);

        this.name=name;
    }
    public EsThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, PriorityBlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        this(name,corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory,new EsAbortPolicy());
    }

}
