package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.RecommendCategory;
import com.spring.mmm.domain.recommends.domain.RecommendedFood;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "recommended_food")
@Entity
public class RecommendedFoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommended_food_id")
    private Integer recommendedFoodId;

    @Column(name = "eaten")
    private Boolean eaten;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private RecommendCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private FoodEntity foodEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_recommend")
    private FoodRecommendEntity foodRecommendEntity;

    public static RecommendedFoodEntity from(RecommendedFood recommendedFood){
        return RecommendedFoodEntity.builder()
                .recommendedFoodId(recommendedFood.getRecommendedFoodId())
                .eaten(recommendedFood.getEaten())
                .category(recommendedFood.getCategory())
                .build();
    }

    public RecommendedFood to(){
        return RecommendedFood.builder()
                .recommendedFoodId(this.recommendedFoodId)
                .eaten(this.eaten)
                .category(this.category)
                .foodId(this.foodEntity.getFoodId())
                .foodRecommendId(this.foodRecommendEntity.getFoodRecommendId())
                .build();
    }
}
