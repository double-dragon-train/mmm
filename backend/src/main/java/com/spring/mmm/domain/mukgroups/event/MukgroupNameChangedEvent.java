package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractBiFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.AbstractFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukgroupNameChangedEvent extends AbstractFunctionMuklogEvent {
    public MukgroupNameChangedEvent(String source, Long mukgroupId) {
        super(source, MuklogEventType.GROUP_NAME_CHANGED, mukgroupId);
    }
}