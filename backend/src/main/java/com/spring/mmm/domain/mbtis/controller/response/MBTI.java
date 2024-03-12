package com.spring.mmm.domain.mbtis.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MBTI {
    Integer EI;
    Integer NS;
    Integer TF;
    Integer JP;
    Integer Mint;
    Integer Pine;
    Integer Die;
}
