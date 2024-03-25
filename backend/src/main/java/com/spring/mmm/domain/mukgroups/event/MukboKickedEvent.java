package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractBiFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukboKickedEvent  extends AbstractBiFunctionMuklogEvent {
    public MukboKickedEvent(String source, String target, Long MukgroupId) {
        super(source, target, MuklogEventType.MUKBO_KICKED, MukgroupId);
    }
}