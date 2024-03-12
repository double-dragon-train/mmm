package com.spring.mmm.domain.mbtis.controller.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionsResponse {
    private Long quizId;
    private String context;
    private String img;
    private List<AnswerResponse> answers;
}
