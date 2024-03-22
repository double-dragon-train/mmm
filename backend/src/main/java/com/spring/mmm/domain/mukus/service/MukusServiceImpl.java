package com.spring.mmm.domain.mukus.service;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
