package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.EatenMukboEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EatenMukboJpaRepository extends JpaRepository<EatenMukboEntity, Long> {

    void deleteAllByFoodRecommendEntity_RecommendDateAndMukboEntity_MukgroupEntity_MukgroupId(LocalDate date, Long groupId);

    Boolean existsByFoodRecommendEntity_RecommendDateAndMukboEntity_MukgroupEntity_MukgroupId(LocalDate date, Long groupId);
}
