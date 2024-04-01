package com.spring.mmm.domain.recommends.service;

import com.spring.mmm.domain.recommends.controller.request.LunchRecommendRequest;
import com.spring.mmm.domain.recommends.controller.response.FoodInformation;
import com.spring.mmm.domain.recommends.controller.response.LunchRecommendFoodInformation;
import com.spring.mmm.domain.recommends.controller.response.NewRecommendedFoodInformation;

import java.util.List;

public interface RecommendService {
    List<FoodInformation> recommendRandomFood();

    List<LunchRecommendFoodInformation> lunchRecommendFood(LunchRecommendRequest lunchRecommendRequest);

    void saveRecommend(Long mukgroupId);

    NewRecommendedFoodInformation newRecommendFood(Long mukgroupId);

}
