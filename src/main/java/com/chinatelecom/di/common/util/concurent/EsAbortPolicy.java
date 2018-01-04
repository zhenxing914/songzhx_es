package com.chinatelecom.di.common.util.concurent;

import com.chinatelecom.di.common.metric.CounterMetric;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by song on 2017/12/5.
 */
public class EsAbortPolicy implements XRejectedExecutionHandler {

    private final CounterMetric rejected = new CounterMetric();

    @Override
    public long reject() {
        return rejected.count();
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

    }
}
