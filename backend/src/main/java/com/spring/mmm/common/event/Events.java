package com.spring.mmm.common.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Objects;

@Slf4j
public class Events {

    private static ApplicationEventPublisher eventPublisher;

    static void setEventPublisher(ApplicationEventPublisher eventPublisher) {

        Events.eventPublisher = eventPublisher;
        log.info("setting publisher!");
    }

    public static void raise(Event event) {
        Objects.requireNonNull(eventPublisher).publishEvent(event);
    }
}
