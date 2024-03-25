package com.spring.mmm.domain.mukgroups.event;

import com.spring.mmm.domain.muklogs.event.AbstractBiFunctionMuklogEvent;
import com.spring.mmm.domain.muklogs.event.MuklogEventType;

public class MukboInvitedEvent extends AbstractBiFunctionMuklogEvent {
    public MukboInvitedEvent(String source, String target, Long MukgroupId) {
        super(source, target, MuklogEventType.MUKBO_INVITED, MukgroupId);
    }
}
