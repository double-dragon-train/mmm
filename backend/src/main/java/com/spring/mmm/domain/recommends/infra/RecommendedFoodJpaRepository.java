package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendedFoodJpaRepository extends JpaRepository<RecommendedFoodEntity, Long> {
    Optional<RecommendedFoodEntity> findByRecommendedFoodId(Long recommendedFoodId);

}
