package com.spring.mmm.domain.mbtis.controller.request;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukBTICalcRequest {
    private Boolean isLogin;
    private Long userId;
    private List<CalcInfo> answers;
}
