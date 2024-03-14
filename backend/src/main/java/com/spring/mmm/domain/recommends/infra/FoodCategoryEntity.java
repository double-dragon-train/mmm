package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.FoodCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food_category")
@Entity
public class FoodCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_category_id")
    private Integer foodCategoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @OneToMany(mappedBy = "foodCategoryEntity")
    private List<FoodEntity> foods;

    public static FoodCategoryEntity from(FoodCategory foodCategory){
        return FoodCategoryEntity.builder()
                .foodCategoryId(foodCategory.getFoodCategoryId())
                .name(foodCategory.getName())
                .color(foodCategory.getColor())
                .build();
    }

    public FoodCategory to(){
        return FoodCategory.builder()
                .foodCategoryId(this.foodCategoryId)
                .name(this.name)
                .color(this.color)
                .build();
    }
}
