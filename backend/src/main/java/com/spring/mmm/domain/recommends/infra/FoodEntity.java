package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.Food;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food")
@Entity
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id")
    private FoodCategoryEntity foodCategoryEntity;

    @OneToMany(mappedBy = "foodEntity", cascade = CascadeType.REMOVE)
    private List<RecommendedFoodEntity> recommendedFoodEntities;

    @OneToMany(mappedBy = "foodEntity", cascade = CascadeType.REMOVE)
    private List<FoodMBTIEntity> foodMBTIEntities;

    public static FoodEntity from(Food food){
        return FoodEntity.builder()
                .foodId(food.getFoodId())
                .name(food.getName())
                .image(food.getImage())
                .build();
    }

    public Food to(){
        return Food.builder()
                .foodId(this.foodId)
                .name(this.name)
                .image(this.image)
                .foodCategoryId(this.foodCategoryEntity.getFoodCategoryId())
                .build();
    }
}
