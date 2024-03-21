package com.spring.mmm.domain.mbtis.controller;

import com.spring.mmm.domain.mbtis.controller.request.CalcInfo;
import com.spring.mmm.domain.mbtis.controller.request.MBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.*;
import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mbtis.domain.MukBTIAnswerEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import com.spring.mmm.domain.mbtis.service.MukBTIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<MBTIResultResponse> calcMBTI(@RequestBody MBTICalcRequest mbtiCalcRequest){
        // 1. 검증
        // 2. 계산
        MBTI mbti = MBTI.builder()
                .EI(20)
                .NS(100)
                .TF(0)
                .JP(80)
                .Mint(100)
                .Pine(100)
                .Die(0)
                .build();

        MBTIResult mbtiResult = MBTIResult.builder()
                .key("123456")
                .mbti(mbti)
                .build();

        MBTIResultResponse mbtiResultResponse = MBTIResultResponse.builder()
                .mbtiResult(mbtiResult)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(mbtiResultResponse);
    }
}
