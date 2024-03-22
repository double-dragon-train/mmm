package com.spring.mmm.domain.mukus.controller;

import com.spring.mmm.domain.mukus.controller.request.RecommendFoodChoiceRequest;
import com.spring.mmm.domain.mukus.controller.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("mukus")
public class MukusMockController {

    @GetMapping("groups/{groupId}")
    public ResponseEntity<MukusResponse> getAllMukus(@PathVariable Long groupId){
        // 1. 그룹 검색
        // 2. 먹어스 데이터 반환
        MukusResponse mukusResponse = MukusResponse.builder()
                .mukusId(1L)
                .date(Instant.now())
                .colorcode("#132436")
                .category("한식")
                .build();
        return ResponseEntity.ok(mukusResponse);
    }

    @GetMapping("groups/{groupId}/date")
    public ResponseEntity<MukusDetailResponse> getDetailMukus(@PathVariable Long groupId){
        // 1.
        Food food = Food.builder()
                .name("돈까스")
                .img("맛있는 로돈추 이미지")
                .build();
        Mukbo mukbo1 = Mukbo.builder()
                .nickname("장사원")
                .build();
        Mukbo mukbo2 = Mukbo.builder()
                .nickname("김부장")
                .build();
        MukusDetailResponse mukusDetailResponse = MukusDetailResponse.builder()
                .food(food)
                .mukbos(Arrays.asList(mukbo1, mukbo2))
                .build();
        return ResponseEntity.ok(mukusDetailResponse);
    }
}
