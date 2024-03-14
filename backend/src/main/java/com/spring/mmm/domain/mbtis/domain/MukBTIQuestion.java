package com.spring.mmm.domain.mbtis.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MukBTIQuestion {
    private Integer questionId;
    private String imageSrc;
    private String content;

    private Integer mukbtiId;
}
