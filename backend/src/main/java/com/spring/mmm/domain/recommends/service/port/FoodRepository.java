package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.recommends.domain.FoodEntity;

public interface FoodRepository {

    FoodEntity findByName(String name);
}
