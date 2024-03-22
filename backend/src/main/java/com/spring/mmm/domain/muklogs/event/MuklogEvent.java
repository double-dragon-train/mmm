package com.spring.mmm.domain.muklogs.event;

import lombok.Getter;


public interface MuklogEvent {


    Long getMukgroupId();
    MuklogEventType getMuklogEventType();
}
