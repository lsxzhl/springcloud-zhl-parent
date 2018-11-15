package com.zhl.eurekaserver.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanceRegisterListener implements ApplicationListener<EurekaInstanceRegisteredEvent> {

    private static final Logger log = LoggerFactory.getLogger(InstanceRegisterListener.class);

    @Override
    public void onApplicationEvent(EurekaInstanceRegisteredEvent event) {
        log.info("服务:{}注册成功！！！" + event.getInstanceInfo().getAppName());
    }

}
