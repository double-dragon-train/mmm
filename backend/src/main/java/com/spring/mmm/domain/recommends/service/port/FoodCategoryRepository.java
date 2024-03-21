package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;

public interface FoodCategoryRepository {
    FoodCategoryEntity findByFoodId(Integer foodId);
}
