package com.zhl.eurekaserver.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanceCancelListener implements ApplicationListener<EurekaInstanceCanceledEvent> {

    private static final Logger log = LoggerFactory.getLogger(InstanceCancelListener.class);

    @Override
    public void onApplicationEvent(EurekaInstanceCanceledEvent event) {
         log.info("服务:{"+event.getAppName()+"}挂了！！！" );
    }

}
