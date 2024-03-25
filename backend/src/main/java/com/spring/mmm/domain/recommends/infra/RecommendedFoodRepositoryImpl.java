package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import com.spring.mmm.domain.recommends.exception.RecommendErrorCode;
import com.spring.mmm.domain.recommends.exception.RecommendException;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class RecommendedFoodRepositoryImpl implements RecommendedFoodRepository {

    private final RecommendedFoodJpaRepository recommendedFoodJpaRepository;

    @Override
    public RecommendedFoodEntity findByRecommendedFoodId(Long recommendedFoodId) {
        return recommendedFoodJpaRepository.findByRecommendedFoodId(recommendedFoodId)
                .orElseThrow(() -> new RecommendException(RecommendErrorCode.RECOMMENDED_NOT_FOUND));
    }

    @Override
    public List<RecommendedFoodEntity> findRecommendedFoodByYearAndMonth(Long mukgroupId, Integer year, Integer month) {
        return recommendedFoodJpaRepository.findRecommendedFoodByYearAndMonth(mukgroupId, year, month);
    }

}
