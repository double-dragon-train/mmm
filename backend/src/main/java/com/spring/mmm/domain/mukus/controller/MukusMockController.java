package com.spring.mmm.domain.mukus.controller;

import com.spring.mmm.domain.mukus.controller.request.RecommendFoodChoiceRequest;
import com.spring.mmm.domain.mukus.controller.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .date(new Date(2024-3-12))
                .colorcode("#132436")
                .category("한식")
                .build();
        return ResponseEntity.ok(mukusResponse);
    }

    @GetMapping("groups/{groupId}/recent")
    public ResponseEntity<MukusRecentResponse> getRecentMukus(@PathVariable Long groupId){
        // 1. 그룹 검색
        // 2. 먹어스 최근 추천 메뉴 확인
        RecommendFood recommendFood1 = RecommendFood.builder()
                .recommendFoodId(1L)
                .name("돈까스")
                .img("맛있는 로돈추 이미지")
                .build();

        RecommendFood recommendFood2 = RecommendFood.builder()
                .recommendFoodId(1L)
                .name("제육볶음")
                .img("맛있는 강남불백 이미지")
                .build();

        RecommendData recommendData = RecommendData.builder()
                .recommendId(1L)
                .date(new Date(2024-3-12))
                .foods(Arrays.asList(recommendFood1, recommendFood2))
                .build();

        MukusRecentResponse mukusRecentResponse = MukusRecentResponse.builder()
                .hasValue(Boolean.TRUE)
                .data(recommendData)
                .build();

        return ResponseEntity.ok(mukusRecentResponse);
    }

    @PostMapping("groups/{groupId}/recent")
    public ResponseEntity<Void> choiceFood(@PathVariable Long groupId, @RequestBody RecommendFoodChoiceRequest  recommendFoodId){
        // 1. request 검증 확인
        // 2. 그룹 검색
        // 3. 선택한 메뉴 반환
        return ResponseEntity.ok().build();
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
