package com.spring.mmm.domain.mukgroups.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukLog {
    String content;
    Long createdAt;
}
