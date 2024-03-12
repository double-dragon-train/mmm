package com.spring.mmm.domain.mukgroups.domain;

import com.spring.mmm.domain.mbtis.controller.response.MBTI;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Mukbo {
    private Long mukboId;
    private String name;
    private Long mukGroupId;
    private Long userId;
    private MukboType type;
    private MBTI mbti;
}
