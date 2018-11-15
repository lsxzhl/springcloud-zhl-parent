package com.zhl.serviceribbon.controller;

import com.zhl.serviceribbon.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping(value = "/hi")
    public String index(@RequestParam("name") String name){
        return indexService.index(name);
    }


}
