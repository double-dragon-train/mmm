package com.spring.mmm.domain.recommends.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecommendedFood {
    private Integer recommendedFoodId;
    private Boolean eaten;
    private RecommendCategory category;

    private Integer foodId;
    private Integer foodRecommendId;
}
