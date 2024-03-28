package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.recommends.controller.response.WeatherDTO;
import com.spring.mmm.domain.recommends.domain.FoodEntity;

import java.util.List;

public interface FoodRepository {

    FoodEntity findByName(String name);

    List<FoodEntity> findAll();

    List<FoodEntity> findByWeatherId(Integer weatherId);
}
