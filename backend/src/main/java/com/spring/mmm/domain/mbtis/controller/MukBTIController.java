package com.spring.mmm.domain.mbtis.controller;

import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.*;
import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import com.spring.mmm.domain.mbtis.service.MukBTIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mbti")
public class MukBTIController {
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

    @PostMapping
    public ResponseEntity<MukBTIResultResponse> calcMBTI(@RequestBody MukBTICalcRequest mukBTICalcRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MukBTIResultResponse.builder()
                        .mukBTIResult(mukBTIService.calcMBTI(mukBTICalcRequest))
                        .build());
    }
}
