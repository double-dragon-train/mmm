package com.spring.mmm.domain.muklogs.event;

import lombok.Getter;

@Getter
public abstract class AbstractFunctionMuklogEvent implements MuklogEvent{
    private String source;
    private MuklogEventType muklogEventType;
    private Long MukgroupId;
}