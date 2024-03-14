package com.spring.mmm.domain.mukgroups.domain;

import com.spring.mmm.domain.mukgroups.controller.response.MukgroupResponse;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mukgroup {
    private Long mukgroupId;
    private String name;
    private String imageSrc;
    private Boolean isSolo;

    public MukgroupResponse to(){
        return MukgroupResponse.builder()
                .mukgroupId(this.mukgroupId)
                .name(this.name)
                .isSolo(this.isSolo)
                .imageSrc(this.imageSrc)
                .build();
    }

    public static Mukgroup create(String name, Boolean isSolo){
        return Mukgroup.builder()
                .name(name)
                .isSolo(isSolo)
                .build();
    }

    public Mukgroup modifyName(String name){
        return Mukgroup.builder()
                .name(name)
                .isSolo(this.isSolo)
                .imageSrc(this.imageSrc)
                .mukgroupId(this.getMukgroupId())
                .build();
    }
}
