package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;

public interface FoodRecommendRepository {
    FoodRecommendEntity save(MukgroupEntity mukgroupEntity, FoodRecommendEntity foodRecommendEntity);

    FoodRecommendEntity findByMukgroupId(Long mukgroupId);
}
