package com.spring.mmm.domain.muklogs.domain;

import lombok.*;

import java.time.Instant;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukLog {
    private Long muklogId;
    private Instant createdAt;
    private String content;

    private Long mukgroupId;
}
