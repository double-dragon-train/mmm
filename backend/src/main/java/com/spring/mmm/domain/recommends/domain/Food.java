package com.spring.mmm.domain.recommends.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Food {
    private Integer foodId;
    private String name;
    private String image;

    private Integer foodCategoryId;
}
