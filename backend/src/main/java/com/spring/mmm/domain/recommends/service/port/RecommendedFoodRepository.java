package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;

import java.util.Optional;

public interface RecommendedFoodRepository {

    RecommendedFoodEntity findByRecommendedFoodId(Long recommendedFoodId);
}
