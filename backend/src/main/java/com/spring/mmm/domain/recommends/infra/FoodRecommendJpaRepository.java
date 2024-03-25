package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodRecommendJpaRepository extends JpaRepository<FoodRecommendEntity, Long> {

    @Query(
            "SELECT fr " +
                    "FROM FoodRecommendEntity fr " +
                    "WHERE fr.mukgroupEntity.mukgroupId = :mukgroupId " +
                    "AND fr.hasValue = false " +
                    "AND fr.recommendDate = (SELECT MAX(fr2.recommendDate) FROM FoodRecommendEntity fr2)"
    )
    Optional<FoodRecommendEntity> findRecommendByMukgroupId(Long mukgroupId);

}
