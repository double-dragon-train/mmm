package com.spring.mmm.domain.mukus.controller.response;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendData {
    private Instant date;
    private List<RecommendFood> foods;

    public static RecommendData create(List foods) {
        return RecommendData.builder()
                .date(Instant.now())
                .foods(foods)
                .build();
    }
}
