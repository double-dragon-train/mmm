package com.spring.mmm.domain.mukgroups.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Mukbo {
    private Long mukboId;
    private String name;
    private MukboType type;

    private Long mukgroupId;
    private Long userId;

    public static Mukbo changeMukgroup(Mukbo mukbo, Long mukgroupId){
        return Mukbo.builder()
                .mukboId(mukbo.getMukboId())
                .name(mukbo.getName())
                .type(mukbo.getType())
                .mukgroupId(mukgroupId)
                .userId(mukbo.userId)
                .build();
    }
}
