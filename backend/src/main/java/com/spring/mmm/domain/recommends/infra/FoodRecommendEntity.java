package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.FoodRecommend;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food_recommend")
@Entity
public class FoodRecommendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_recommend_id")
    private Integer foodRecommendId;

    @Column(name = "recommend_date")
    private Instant recommendDate;

    @OneToMany(mappedBy = "foodRecommendEntity", cascade = CascadeType.REMOVE)
    private List<RecommendedFoodEntity> recommendedFoodEntities;

    @OneToMany(mappedBy = "foodRecommendEntity", cascade = CascadeType.REMOVE)
    private List<EatenMukboEntity> eatenMukboEntities;

    public static FoodRecommendEntity from(FoodRecommend foodRecommend){
        return FoodRecommendEntity.builder()
                .foodRecommendId(foodRecommend.getFoodRecommendId())
                .recommendDate(foodRecommend.getRecommendDate())
                .build();
    }

    public FoodRecommend to(){
        return FoodRecommend.builder()
                .foodRecommendId(this.foodRecommendId)
                .recommendDate(this.recommendDate)
                .build();
    }
}
