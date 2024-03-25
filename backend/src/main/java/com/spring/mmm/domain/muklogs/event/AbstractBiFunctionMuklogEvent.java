package com.spring.mmm.domain.muklogs.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractBiFunctionMuklogEvent  implements MuklogEvent{

    private String source;
    private String target;
    private MuklogEventType muklogEventType;
    private Long MukgroupId;
}
