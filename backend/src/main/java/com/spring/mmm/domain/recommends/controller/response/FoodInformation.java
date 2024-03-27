package com.spring.mmm.domain.recommends.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodInformation {
    private Integer foodId;
    private String name;
    private String imageSrc;
}
