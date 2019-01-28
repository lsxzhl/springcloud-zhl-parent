package com.zhl.serviceribbon.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AvoidScan
public class LoadBalanceConfig {

    private IClientConfig iClientConfig;

    @Autowired
    public void setiClientConfig(IClientConfig iClientConfig){
        this.iClientConfig = iClientConfig;
    }

    public IRule ribbonRule(IClientConfig iClientConfig){
        return new RandomRule();
    }




}
