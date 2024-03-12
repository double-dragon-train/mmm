package com.spring.mmm.domain.mukgroups.controller.response;

import com.spring.mmm.domain.mbtis.controller.response.MBTI;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupResponse {
    private Long groupId;
    private String name;
    private Boolean solo;
    private MBTI mbti;
    private Long titleMukjukId;
}
