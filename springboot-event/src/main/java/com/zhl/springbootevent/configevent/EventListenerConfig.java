package com.zhl.springbootevent.configevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

@Configuration
@Slf4j
public class EventListenerConfig {

    @EventListener
    public void handleEvent(Object event){
        log.info("事件：{}", event);
    }

    @EventListener
    public void handleCustomEvent(CustomEvent customEvent){
        //监听 CustomEvent事件
        log.info("监听到CustomEvent事件，消息为：{}, 发布时间：{}", customEvent.getMessageEntity(), customEvent.getTimestamp());
    }

    @Async
    @EventListener(condition = "#customEvent.messageEntity.code == 'okong'")
    public void handleCustomEventByCondition(CustomEvent customEvent){
        //监听 CustomEvent事件
        log.info("监听到code为'oKong'的CustomEvent事件，消息为：{}, 发布时间：{}", customEvent.getMessageEntity(), customEvent.getTimestamp());
    }

    @EventListener
    public void handleObjectEvent(MessageEntity messageEntity) {
        //这个和eventbus post方法一样了
        log.info("监听到对象事件，消息为：{}", messageEntity);

    }

}
