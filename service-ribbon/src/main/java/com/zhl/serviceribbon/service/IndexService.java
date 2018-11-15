package com.zhl.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IndexService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String index(String name){
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name,String.class);
    }

    public String hiError(String name){
        return "Hello "+ name + " Sorry Error!!!";
    }


}
