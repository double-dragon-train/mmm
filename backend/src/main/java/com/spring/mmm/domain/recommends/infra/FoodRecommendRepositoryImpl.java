package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoodRecommendRepositoryImpl implements FoodRecommendRepository {

    private final FoodRecommendJpaRepository foodRecommendJpaRepository;

    @Override
    public FoodRecommendEntity save(MukgroupEntity mukgroupEntity, FoodRecommendEntity foodRecommendEntity) {
        return null;
    }

    @Override
    public FoodRecommendEntity findByMukgroupId(Long mukgroupId) {
        return null;
    }
}
