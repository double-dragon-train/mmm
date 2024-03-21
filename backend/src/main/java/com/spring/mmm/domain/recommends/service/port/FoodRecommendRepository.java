package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;

import java.util.Optional;

public interface FoodRecommendRepository {
    void save(MukgroupEntity mukgroupEntity, FoodRecommendEntity foodRecommendEntity);

    FoodRecommendEntity findByMukgroupId(Long mukgroupId);
}
