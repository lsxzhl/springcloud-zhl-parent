package com.zhl.springbootevent.configevent;

import org.springframework.context.ApplicationEvent;

/**
 * 编写事件源
 */
@SuppressWarnings("serial")
public class CustomEvent extends ApplicationEvent {

    private MessageEntity messageEntity;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CustomEvent(Object source, MessageEntity messageEntity) {
        super(source);
        this.messageEntity = messageEntity;
    }

    public MessageEntity getMessageEntity() {
        return messageEntity;
    }
}
