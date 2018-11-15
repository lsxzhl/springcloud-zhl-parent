package com.zhl.servicehi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/hi")
    public String index(@RequestParam("name") String name){
        return "Hello " + name + " I am from port:" + port;
    }

}
