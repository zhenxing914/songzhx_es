package com.chinatelecom.di.transport;

import com.chinatelecom.di.common.io.stream.Writeable;

/**
 * Created by song on 2018/2/12.
 */
public interface TransportAddress  extends Writeable<TransportAddress>{

    /**
     * Return the host string for this transport address
     * @return
     */
    String getHost();

}
