package com.chinatelecom.di.bootstrap;

import com.chinatelecom.di.node.Node;
import com.chinatelecom.di.node.NodeBuilder;

import java.util.concurrent.CountDownLatch;

/**
 * Created by song on 2017/10/16.
 */
public class Bootstrap {

    private static Bootstrap INSTANCE;

    public Node node;

    private final CountDownLatch keepAliveLatch=new CountDownLatch(1);

    private final Thread keepAliveThread;

     Bootstrap(){
         System.out.println("Bootstrap structure.");
         keepAliveThread=new Thread(new Runnable() {
            public void run() {
                try {
                    keepAliveLatch.await();
                } catch (InterruptedException e) {
                    //bail out

                }
            }
        });
        keepAliveThread.setDaemon(false);

        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                System.out.println("ctr+c in .");
                keepAliveLatch.countDown();
            }
        });


    }

    static public void init(){
        System.out.println("init .....");
        INSTANCE=new Bootstrap();

        INSTANCE.setup();
        INSTANCE.start();

    }

    public void  start(){
        System.out.println("start ...");
        node.start();
        keepAliveThread.start();

    }

    public void setup(){
        System.out.println("Bootstrap Setup");

        NodeBuilder nodeBuilder=new NodeBuilder();
        node=nodeBuilder.build();
    }

}
