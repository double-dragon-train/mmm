package com.spring.mmm.domain.mukus.service;

import com.spring.mmm.domain.mukus.controller.response.FoodCategory;
import com.spring.mmm.domain.mukus.controller.response.MukusCalendarResponse;
import com.spring.mmm.domain.mukus.controller.response.MukusDayResponse;
import com.spring.mmm.domain.mukus.controller.response.RecommendFood;
import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MukusServiceImpl implements MukusService {

    private final RecommendedFoodRepository recommendedFoodRepository;

    @Override
    @Transactional
    public void selectRecentMukus(Long recommendFoodId) {

        RecommendedFoodEntity recommendedFoodEntity =
                recommendedFoodRepository.findByRecommendedFoodId(recommendFoodId);

        recommendedFoodEntity.eat();

    }

    @Override
    @Transactional(readOnly = true)
    public MukusCalendarResponse getMukusMonth(Long groupId, Integer year, Integer month) {
        List<RecommendedFoodEntity> recommendedFoodEntities =
                recommendedFoodRepository.findRecommendedFoodByYearAndMonth(groupId, year, month);

        List<MukusDayResponse> mukusMonthResponse = recommendedFoodEntities.stream().map(MukusDayResponse::create).toList();

        MukusCalendarResponse mukusCalendarResponse = MukusCalendarResponse.create(mukusMonthResponse);

        return mukusCalendarResponse;
    }

}
