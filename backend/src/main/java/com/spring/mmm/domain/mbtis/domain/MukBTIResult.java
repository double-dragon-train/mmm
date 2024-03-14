package com.spring.mmm.domain.mbtis.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MukBTIResult {
    private Integer resultId;
    private Integer score;

    private Integer mukbtiId;
    private Long mukboId;
    private Long userId;
}
