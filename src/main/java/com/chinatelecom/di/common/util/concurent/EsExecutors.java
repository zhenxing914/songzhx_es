package com.chinatelecom.di.common.util.concurent;

import com.chinatelecom.di.common.settings.Settings;
import com.google.common.base.Joiner;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by song on 2017/12/5.
 */
public class EsExecutors {

    public static PrioritizedEsThreadPoolExecutor newSinglePrioritizing(String name, ThreadFactory threadFactory)
    {
        return new PrioritizedEsThreadPoolExecutor(name,1,1,0L, TimeUnit.MILLISECONDS,threadFactory);
    }

    public static ThreadFactory daemonThreadFactory(Settings settings , String namePrefix){
        return daemonThreadFactory(threadName(settings,namePrefix));
    }



    public static ThreadFactory daemonThreadFactory(Settings settings , String... names){

        return daemonThreadFactory(threadName(settings,names));
    }

    public static ThreadFactory daemonThreadFactory(String namePrefix){
        return new EsThreadFactory(namePrefix);
    }


    public static String threadName(Settings settings,String namePrefix){
        String name =settings.get("name");
        if(name==null)
        {
            name="elasticsearch";
        }else{
            name="elasticsearch["+namePrefix+"]";
        }
        return name;
    }

    public static String threadName(Settings settings,String ... names)
    {
        return threadName(settings,"["+ Joiner.on(".").skipNulls().join(names)+"]");
    }

    static class EsThreadFactory implements ThreadFactory{
        final String namePrefix;
        final AtomicInteger threadNumber=new AtomicInteger(1);
        final ThreadGroup group;

        public EsThreadFactory(String namePrefix){
            this.namePrefix=namePrefix;
            SecurityManager s=System.getSecurityManager();
            group=(s!=null)?s.getThreadGroup():
                    Thread.currentThread().getThreadGroup();
        }

        @Override
        public Thread newThread(Runnable r) {

            Thread t=new Thread(group,r,
                    namePrefix+"[T#"+threadNumber.getAndIncrement()+"]");
            return t;
        }
    }
}
