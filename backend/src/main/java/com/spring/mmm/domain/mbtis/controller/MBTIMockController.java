package com.spring.mmm.domain.mbtis.controller;

import com.spring.mmm.domain.mbtis.controller.request.MBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.*;
import com.spring.mmm.domain.mbtis.domain.MBTI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mbti")
public class MBTIMockController {

    @GetMapping
    public ResponseEntity<QuestionsResponse> sendQuestion(){
        List<AnswerResponse> answers = new ArrayList<>();
        AnswerResponse answerResponse = AnswerResponse.builder()
                .answerId(1L)
                .answerContext("답변1")
                .answerImage("답변이미지1")
                .build();
        answers.add(answerResponse);
        QuestionsResponse questionsResponse = QuestionsResponse.builder()
                .quizId(1L)
                .context("질문1")
                .img("질문이미지1")
                .answers(answers)
                .build();
        return ResponseEntity.ok(questionsResponse);
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
