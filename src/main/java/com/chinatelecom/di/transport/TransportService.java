package com.chinatelecom.di.transport;

import com.chinatelecom.di.ThreadPool.ThreadPool;
import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.tasks.TaskManager;
import com.google.inject.Inject;


/**
 * Created by song on 2017/11/24.
 */
public class TransportService extends AbstractLifecycleComponent<TransportService> {

    protected final Transport transport;
    protected final TaskManager taskManager;
    protected final ThreadPool threadPool;

   // private final TransportService.Adapter adapter;



    @Inject
    public TransportService(Settings settings, Transport transport, ThreadPool threadPool){

        super(settings);
        this.transport=transport;
        this.threadPool=threadPool;
        taskManager=createTaskManager();
    }

    private TaskManager createTaskManager() {
        return new TaskManager(settings);
    }


    @Override
    protected void doStart() {

        //adapter.rxMetric.clear();
        transport.start();


    }

    public TaskManager getTaskManger() {
        return taskManager;
    }

    protected class Adapter implements TransportServiceAdapter {


        @Override
        public void received(long size) {

        }

        @Override
        public void sent(long size) {

        }
    }
}
