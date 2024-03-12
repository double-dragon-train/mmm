package com.spring.mmm.domain.mukus.controller.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendData {
    private Long recommendId;
    private Date date;
    private List<RecommendFood> foods;
}
