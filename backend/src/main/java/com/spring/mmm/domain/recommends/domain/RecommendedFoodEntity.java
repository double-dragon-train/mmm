package com.spring.mmm.domain.recommends.domain;

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
}
