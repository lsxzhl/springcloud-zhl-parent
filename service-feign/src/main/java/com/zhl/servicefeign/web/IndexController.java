package com.zhl.servicefeign.web;

import com.zhl.servicefeign.client.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/hi")
    public String getName(@RequestParam("name") String name){
        return schedualServiceHi.getName(name);
    }


}
