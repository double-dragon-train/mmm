package com.spring.mmm.domain.mukus.controller.response;

import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.service.port.FoodCategoryRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodCategory {
    private String name;
    private String color;

    public static FoodCategory create(String name, String color) {

        return FoodCategory.builder()
                .name(name)
                .color(color)
                .build();
    }
}
