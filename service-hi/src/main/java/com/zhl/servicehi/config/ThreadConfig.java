package com.zhl.servicehi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/7/29 15:54
 */
@Configuration
@EnableAsync
public class ThreadConfig {

    @Bean
    public Executor getExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setQueueCapacity(250);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
