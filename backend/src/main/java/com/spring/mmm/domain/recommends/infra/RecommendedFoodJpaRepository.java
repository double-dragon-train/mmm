package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecommendedFoodJpaRepository extends JpaRepository<RecommendedFoodEntity, Long> {

    Optional<RecommendedFoodEntity> findByRecommendedFoodId(Long recommendedFoodId);

    @Query("SELECT r FROM RecommendedFoodEntity r " +
            "JOIN FETCH r.foodRecommendEntity f " +
            "WHERE r.eaten = true " +
            "and f.mukgroupEntity.mukgroupId = :mukgroupId " +
            "and YEAR(f.recommendDate) = :year AND " +
            "MONTH(f.recommendDate) = :month")
    List<RecommendedFoodEntity> findRecommendedFoodByYearAndMonth(Long mukgroupId, Integer year, Integer month);

}
