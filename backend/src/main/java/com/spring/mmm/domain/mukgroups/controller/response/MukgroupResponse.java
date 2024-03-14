package com.spring.mmm.domain.mukgroups.controller.response;

import com.spring.mmm.domain.mbtis.controller.response.MBTI;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukgroupResponse {
    private Long mukgroupId;
    private String name;
    private Boolean isSolo;
    private String imageSrc;
    private Long titleMukjukId;
}
