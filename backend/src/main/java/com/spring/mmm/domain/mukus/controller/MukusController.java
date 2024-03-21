package com.spring.mmm.domain.mukus.controller;

import com.spring.mmm.domain.mukus.controller.response.FoodCategory;
import com.spring.mmm.domain.mukus.controller.response.MukusRecentResponse;
import com.spring.mmm.domain.mukus.controller.response.RecommendData;
import com.spring.mmm.domain.mukus.controller.response.RecommendFood;
import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import com.spring.mmm.domain.recommends.service.port.FoodCategoryRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("mukus")
public class MukusController {

    private FoodRepository foodRepository;
    private FoodCategoryRepository foodCategoryRepository;
    private FoodRecommendRepository foodRecommendRepository;

    @GetMapping("groups/{groupId}/recent")
    public ResponseEntity<MukusRecentResponse> getRecentMukus(@PathVariable Long groupId){

        FoodRecommendEntity foodRecommendEntity = foodRecommendRepository.findByMukgroupId(groupId);

        List<RecommendedFoodEntity> recommendedFoodEntities = foodRecommendEntity.getRecommendedFoodEntities();

        List<RecommendFood> recommendFoods = recommendedFoodEntities.stream().map(food -> {
            FoodCategoryEntity category = foodCategoryRepository.findByFoodId(food.getFoodEntity().getFoodId());
            FoodCategory foodCategory = FoodCategory.create(category.getName(), category.getColor());
            return RecommendFood.create(foodCategory, food);
        }).toList();


        RecommendData recommendData = RecommendData.create(recommendFoods);


        return ResponseEntity.ok(MukusRecentResponse.create(recommendData));
    }




}
