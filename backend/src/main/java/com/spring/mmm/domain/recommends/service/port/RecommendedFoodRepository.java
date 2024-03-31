package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;

import java.util.List;
import java.util.Optional;

public interface RecommendedFoodRepository {

    Optional<RecommendedFoodEntity> findByRecommendedFoodId(Long recommendedFoodId);

    List<RecommendedFoodEntity> findRecommendedFoodByYearAndMonth(Long mukgroupId, Integer year, Integer month);

    Optional<RecommendedFoodEntity> findRecommendedFoodByDate(Long mukgroupId, Integer year, Integer month, Integer day);

    List<Integer> findAllFoodIdByMukgroupId(Long mukgroupId);

    void save(RecommendedFoodEntity recommendedFoodEntity);
}
