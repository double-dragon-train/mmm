package com.spring.mmm.domain.recommends.domain;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodCategory {
    private Integer foodCategoryId;
    private String name;
    private String color;
}
