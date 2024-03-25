package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;

import java.util.List;
import java.util.Optional;

public interface RecommendedFoodRepository {

    RecommendedFoodEntity findByRecommendedFoodId(Long recommendedFoodId);

    List<RecommendedFoodEntity> findRecommendedFoodByYearAndMonth(Long mukgroupId, Integer year, Integer month);

}
