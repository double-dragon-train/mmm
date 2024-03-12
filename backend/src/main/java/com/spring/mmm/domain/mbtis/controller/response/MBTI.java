package com.spring.mmm.domain.mbtis.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MBTI {
    private Integer EI;
    private Integer NS;
    private Integer TF;
    private Integer JP;
    private Integer Mint;
    private Integer Pine;
    private Integer Die;
}
