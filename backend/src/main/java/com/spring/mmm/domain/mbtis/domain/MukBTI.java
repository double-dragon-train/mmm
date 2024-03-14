package com.spring.mmm.domain.mbtis.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MukBTI {
    private Integer mukbtiId;
    private MukBTIType type;
}
