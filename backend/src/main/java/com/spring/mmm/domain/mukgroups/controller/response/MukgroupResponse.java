package com.spring.mmm.domain.mukgroups.controller.response;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
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

    public static MukgroupResponse createByMukgroupEntity(MukgroupEntity mukgroupEntity){
        return MukgroupResponse.builder()
                .mukgroupId(mukgroupEntity.getMukgroupId())
                .name(mukgroupEntity.getName())
                .isSolo(mukgroupEntity.getIsSolo())
                .imageSrc(mukgroupEntity.getImageSrc())
                .build();
    }
}
