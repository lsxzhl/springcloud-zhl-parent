package com.zhl.springbootevent.controller;

import com.zhl.springbootevent.configevent.CustomEvent;
import com.zhl.springbootevent.configevent.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
@Slf4j
public class DemoController {

    /**
     * 注入 事件发布类
     */
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping
    public String push(String code,String message) {
        log.info("发布applicationEvent事件:{},{}", code, message);
        eventPublisher.publishEvent(new CustomEvent(this, MessageEntity.builder().code(code).message(message).build()));
        return "事件发布成功!";
    }

    @GetMapping("/obj")
    public String pushObject(String code,String message) {
        log.info("发布对象事件:{},{}", code, message);
        eventPublisher.publishEvent(MessageEntity.builder().code(code).message(message).build());
        return "对象事件发布成功!";
    }

}
