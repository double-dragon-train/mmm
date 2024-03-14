package com.spring.mmm.domain.mbtis.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MukBTIAnswer {
    private Integer answerId;
    private String content;
    private Integer score;
    private String imageSrc;

    private Integer mukbtiQuestionId;
}
