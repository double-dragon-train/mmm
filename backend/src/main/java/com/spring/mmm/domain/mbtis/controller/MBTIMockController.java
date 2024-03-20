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
        List<Question> questions = new ArrayList<>();

        for(int i = 1; i <= 15; i++) {
            List<AnswerResponse> answers = new ArrayList<>();
            int ansNum = i % 3 == 0 ? 2 : 5;
            for(int j = 1; j <= ansNum; j++) {
                AnswerResponse answerResponse = AnswerResponse.builder()
                        .answerId(Long.valueOf(j))
                        .answerContext("답변" + j)
                        .answerImage("답변이미지" + j)
                        .build();
                answers.add(answerResponse);
            }
            Question questionsResponse = Question.builder()
                    .quizId(Long.valueOf(i))
                    .context("질문" + i)
                    .img("질문이미지" + i)
                    .answers(answers)
                    .build();
            questions.add(questionsResponse);
        }
        return ResponseEntity.ok(QuestionsResponse.builder().questions(questions).build());
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
