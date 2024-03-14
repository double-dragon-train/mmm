package com.spring.mmm.domain.mukjuks.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mukjuk {
    private Long mukjukId;
    private String name;
    private String context;
    private String imageSrc;
}
