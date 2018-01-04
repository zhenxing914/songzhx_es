package com.chinatelecom.di.common.util.concurent;

import java.util.concurrent.*;

/**
 * Created by song on 2017/12/1.
 */
public class PrioritizedEsThreadPoolExecutor extends EsThreadPoolExecutor
{

    public PrioritizedEsThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory) {
        super(name,corePoolSize,maximumPoolSize,keepAliveTime,unit,new PriorityBlockingQueue<Runnable>(),threadFactory);
    }
}
