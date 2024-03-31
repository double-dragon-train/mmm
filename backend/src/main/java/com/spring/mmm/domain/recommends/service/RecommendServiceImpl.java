package com.spring.mmm.domain.recommends.service;

import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.recommends.controller.request.LunchRecommendRequest;
import com.spring.mmm.domain.recommends.controller.response.FoodInformation;
import com.spring.mmm.domain.recommends.controller.response.LunchRecommendFoodInformation;
import com.spring.mmm.domain.recommends.controller.response.NewRecommendedFoodInformation;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.domain.FoodMBTIEntity;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import com.spring.mmm.domain.weathers.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendServiceImpl implements RecommendService{

    private final FoodRecommendRepository foodRecommendRepository;
    private final MukgroupRepository mukgroupRepository;
    private final RecommendedFoodRepository recommendedFoodRepository;

    private final FoodRepository foodRepository;
    private final WeatherService weatherService;

    @Override
    public List<FoodInformation> recommendRandomFood() {
        int randFirst = (int) (Math.random() * 9) + 1;
        int randSecond = (int) (Math.random() * randFirst);
        return foodRepository.findAll().stream()
                .map(FoodInformation::createByFoodEntity)
                .filter(item -> item.getFoodId() % randFirst == randSecond)
                .limit(7)
                .collect(Collectors.toList());
    }

    @Override
    public List<LunchRecommendFoodInformation> lunchRecommendFood(Long groupId, LunchRecommendRequest lunchRecommendRequest) {

        MukgroupEntity mukgroup = mukgroupRepository.findByMukgroupId(groupId)
                .orElseThrow();
        FoodRecommendEntity foodRecommendEntity =
                FoodRecommendEntity.create(mukgroup, )

        return foodRepository.findAll().stream()
                .sorted((o1, o2) -> getScoreByFoodMukBTI(lunchRecommendRequest, o1, o2))
                .map(foodEntity -> {
                    RecommendedFoodEntity recommendedFoodEntity =
                            RecommendedFoodEntity.create(foodEntity,);
                    recommendedFoodRepository.save(recommendedFoodEntity);
                    return LunchRecommendFoodInformation.create(foodEntity);
                })
                .limit(7)
                .collect(Collectors.toList());
    }

    @Override
    public NewRecommendedFoodInformation newRecommendFood(Long mukgroupId) {
        List<Integer> eatenFoodIds = recommendedFoodRepository.findAllFoodIdByMukgroupId(mukgroupId);
        List<FoodEntity> foods =
                foodRepository.findAll()
                .stream()
                .filter(item -> !eatenFoodIds.contains(item.getFoodId()))
                .collect(Collectors.toList());

        Collections.shuffle(foods);

        return NewRecommendedFoodInformation.create(foods.get(0));
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
