package com.spring.mmm.domain.mukus.controller.response;

import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukusDayResponse {

    private LocalDate date;
    private String colorCode;
    private String category;

    public static MukusDayResponse create(FoodRecommendEntity recommend) {
        return MukusDayResponse.builder()
                .date(recommend.getRecommendDate())
                .colorCode()
                .category()
                .build();
    }
}
