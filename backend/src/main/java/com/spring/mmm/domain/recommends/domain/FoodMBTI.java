package com.spring.mmm.domain.recommends.domain;

import com.spring.mmm.domain.mbtis.domain.MukBTI;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodMBTI {
    private Integer foodMbtiId;
    private Integer score;

    private Integer foodId;
    private Integer mukbtiId;
}
