package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepository {

    private final FoodJpaRepository foodJpaRepository;

    @Override
    public FoodEntity findByName(String name) {
        return foodJpaRepository.findByName(name);
    }
}
