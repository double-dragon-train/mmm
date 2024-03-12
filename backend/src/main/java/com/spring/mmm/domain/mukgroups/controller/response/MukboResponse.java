package com.spring.mmm.domain.mukgroups.controller.response;

import com.spring.mmm.domain.mbtis.controller.response.MBTI;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukboResponse {
    private MukboType type;
    private String name;
    private MBTI mbti;
}
