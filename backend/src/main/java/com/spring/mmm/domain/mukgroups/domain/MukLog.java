package com.spring.mmm.domain.mukgroups.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukLog {
    private String content;
    private Long createdAt;
}
