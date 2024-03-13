package com.spring.mmm.domain.mukjuks.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Badge {
    private Long id;
    private String name;
    private String condition;
    private Boolean isCleared;
    private String imageSrc;
}
