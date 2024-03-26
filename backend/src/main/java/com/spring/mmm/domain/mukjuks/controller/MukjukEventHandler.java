package com.spring.mmm.domain.mukjuks.controller;

import com.spring.mmm.domain.mukjuks.event.FoodRecordedEvent;
import com.spring.mmm.domain.mukjuks.event.MukBTICalculatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MukjukEventHandler {

    @EventListener
    public void handleFoodRecordedEvent(FoodRecordedEvent event){

    }
    @EventListener
    public void handleMukBTICalculatedEvent(MukBTICalculatedEvent event){

    }
}
