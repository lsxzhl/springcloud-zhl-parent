package com.zhl.servicehi.config;

import com.zhl.servicehi.fallback.MyEurekaFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FallbackConfig {

    @Bean
    public MyEurekaFallback eurekaFallback(){
        return new MyEurekaFallback();
    }

}
