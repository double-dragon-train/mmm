package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
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

    @Query("SELECT r FROM RecommendedFoodEntity r " +
            "JOIN FETCH r.foodRecommendEntity f " +
            "WHERE r.eaten = true " +
            "AND f.mukgroupEntity.mukgroupId = :mukgroupId " +
            "AND YEAR(f.recommendDate) = :year " +
            "AND MONTH(f.recommendDate) = :month " +
            "AND DAY(f.recommendDate) = :day")
    Optional<RecommendedFoodEntity> findRecommendedFoodByDate(Long mukgroupId, Integer year, Integer month, Integer day);

    @Query("select fre.foodEntity.foodId from RecommendedFoodEntity fre where fre.eaten = true and fre.foodRecommendEntity.mukgroupEntity.mukgroupId=:mukgroupId")
    List<Integer> findAllFoodIdByMukgroupId(Long mukgroupId);

    Boolean existsByFoodRecommendEntity_RecommendDateAndFoodRecommendEntity_MukgroupEntity_MukgroupId(LocalDate date, Long groupId);

    void deleteAllByFoodRecommendEntity_RecommendDateAndFoodRecommendEntity_MukgroupEntity_MukgroupId(LocalDate date, Long groupId);
}
