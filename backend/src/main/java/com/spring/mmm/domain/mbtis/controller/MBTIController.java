package com.spring.mmm.domain.mbtis.controller;

import com.spring.mmm.domain.mbtis.controller.response.AnswerResponse;
import com.spring.mmm.domain.mbtis.controller.response.Question;
import com.spring.mmm.domain.mbtis.controller.response.QuestionsResponse;
import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import com.spring.mmm.domain.mbtis.service.MukBTIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mbti")
public class MBTIController {
    private final MukBTIService mukBTIService;
    @GetMapping
    public ResponseEntity<QuestionsResponse> sendQuestion(){
        List<MukBTIQuestionEntity> mukBTIQuestions = mukBTIService.findAllMukBTIQuestion();
        return ResponseEntity.ok(QuestionsResponse.builder()
                .questions(mukBTIQuestions
                        .stream()
                        .map(item -> item.toQuestion())
                        .toList())
                .build());
    }
}
