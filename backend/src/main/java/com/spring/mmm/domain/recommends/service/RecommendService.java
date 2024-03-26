package com.spring.mmm.domain.recommends.service;

import com.spring.mmm.domain.recommends.controller.response.FoodInformation;

import java.util.List;

public interface RecommendService {
    List<FoodInformation> recommendRandomFood();
}
