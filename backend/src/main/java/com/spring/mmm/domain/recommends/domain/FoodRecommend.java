package com.spring.mmm.domain.recommends.domain;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodRecommend {
    private Integer foodRecommendId;
    private Instant recommendDate;
}
