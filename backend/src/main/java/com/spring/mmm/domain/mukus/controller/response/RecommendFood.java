package com.spring.mmm.domain.mukus.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendFood {
    private Long recommendFoodId;
    private String name;
    private String img;
}
