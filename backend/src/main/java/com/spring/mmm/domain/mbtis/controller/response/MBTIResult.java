package com.spring.mmm.domain.mbtis.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MBTIResult {
    String key;
    MBTI mbti;
}
