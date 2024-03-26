package com.spring.mmm.domain.recommends.service;

import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.recommends.controller.response.FoodInformation;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendServiceImpl implements RecommendService{

    private final FoodRecommendRepository foodRecommendRepository;
    private final MukgroupRepository mukgroupRepository;

    private final FoodRepository foodRepository;

    @Override
    public List<FoodInformation> recommendRandomFood() {
        int randFirst = (int) (Math.random() * 9) + 1;
        int randSecond = (int) (Math.random() * randFirst);
        return foodRepository.findAll().stream()
                .map(FoodEntity::toFoodInformation)
                .filter(item -> item.getFoodId() % randFirst == randSecond)
                .limit(7)
                .collect(Collectors.toList());
    }
}
