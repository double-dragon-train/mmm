package com.spring.mmm.domain.mukgroups.controller.request;

import com.spring.mmm.domain.mbtis.controller.response.MBTI;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukgroupMBTIResponse {
    private MBTI mbti;
}
