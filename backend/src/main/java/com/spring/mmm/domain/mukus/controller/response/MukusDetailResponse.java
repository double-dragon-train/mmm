package com.spring.mmm.domain.mukus.controller.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukusDetailResponse {
    private Food food;
    private List<Mukbo> mukbos;
}
