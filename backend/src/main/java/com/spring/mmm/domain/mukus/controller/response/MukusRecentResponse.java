package com.spring.mmm.domain.mukus.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukusRecentResponse {
    private Boolean hasValue;
    private RecommendData data;
}
