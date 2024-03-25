package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractBiFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukbotDeletedEvent extends AbstractBiFunctionMuklogEvent {
    public MukbotDeletedEvent(String source, String target, Long MukgroupId) {
        super(source, target, MuklogEventType.MUKBOT_DELETED, MukgroupId);
    }
}
