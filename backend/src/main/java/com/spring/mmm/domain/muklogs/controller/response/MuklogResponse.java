package com.spring.mmm.domain.muklogs.controller.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MuklogResponse {
    private String content;
    private Long createdAt;
}
