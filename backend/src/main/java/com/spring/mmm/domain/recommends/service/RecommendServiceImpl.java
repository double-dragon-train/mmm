package com.spring.mmm.domain.recommends.service;

import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.recommends.controller.request.LunchRecommendRequest;
import com.spring.mmm.domain.recommends.controller.response.FoodInformation;
import com.spring.mmm.domain.recommends.controller.response.LunchRecommendFoodInformation;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.domain.FoodMBTIEntity;
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

    @Override
    public List<LunchRecommendFoodInformation> lunchRecommendFood(LunchRecommendRequest lunchRecommendRequest) {
        return foodRepository.findAll().stream()
                .sorted((o1, o2) -> getScoreByFoodMukBTI(lunchRecommendRequest, o1, o2))
                .map(LunchRecommendFoodInformation::create)
                .limit(7)
                .collect(Collectors.toList());
    }

    private int getScoreByFoodMukBTI(LunchRecommendRequest lunchRecommendRequest, FoodEntity foodOne, FoodEntity foodTwo){
        int sumOne = 0, sumTwo = 0;
        List<FoodMBTIEntity> foodOneMukBTI = foodOne.getFoodMBTIEntities();
        List<FoodMBTIEntity> foodTwoMukBTI = foodTwo.getFoodMBTIEntities();

        for(FoodMBTIEntity foodMBTIEntity : foodOneMukBTI){
            switch (foodMBTIEntity.getMukBTIEntity().getType()){
                case EI -> sumOne += Math.abs(lunchRecommendRequest.getEI() - foodMBTIEntity.getScore());
                case NS -> sumOne += Math.abs(lunchRecommendRequest.getNS() - foodMBTIEntity.getScore());
                case TF -> sumOne += Math.abs(lunchRecommendRequest.getTF() - foodMBTIEntity.getScore());
                case JP -> sumOne += Math.abs(lunchRecommendRequest.getJP() - foodMBTIEntity.getScore());
            }
        }

        for(FoodMBTIEntity foodMBTIEntity : foodTwoMukBTI){
            switch (foodMBTIEntity.getMukBTIEntity().getType()){
                case EI -> sumTwo += Math.abs(lunchRecommendRequest.getEI() - foodMBTIEntity.getScore());
                case NS -> sumTwo += Math.abs(lunchRecommendRequest.getNS() - foodMBTIEntity.getScore());
                case TF -> sumTwo += Math.abs(lunchRecommendRequest.getTF() - foodMBTIEntity.getScore());
                case JP -> sumTwo += Math.abs(lunchRecommendRequest.getJP() - foodMBTIEntity.getScore());
            }
        }

        return Integer.compare(sumOne, sumTwo);
    }
}
