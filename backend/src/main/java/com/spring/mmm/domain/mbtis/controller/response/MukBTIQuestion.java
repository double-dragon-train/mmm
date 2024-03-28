package com.spring.mmm.domain.mbtis.controller.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukBTIQuestion {
    private Integer quizId;
    private String context;
    private String img;
    private List<MukBTIAnswerResponse> answers;
}
