package com.spring.mmm.domain.mukgroups.domain;

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

    private Long mukjukId;
}
