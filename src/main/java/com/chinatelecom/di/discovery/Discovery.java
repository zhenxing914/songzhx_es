package com.chinatelecom.di.discovery;

import com.chinatelecom.di.cluster.routing.RoutingService;
import com.chinatelecom.di.common.component.LifecycleComponent;

/**
 * Created by song on 2017/11/28.
 */
public interface Discovery extends LifecycleComponent<Discovery> {


     void setRoutingService(RoutingService routingService) ;


}
