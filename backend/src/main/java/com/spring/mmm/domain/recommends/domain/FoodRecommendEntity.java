package com.spring.mmm.domain.recommends.domain;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column(name = "has_value")
    private Boolean hasValue;

    @Column(name = "recommend_date")
    private LocalDate recommendDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukgroup_id")
    private MukgroupEntity mukgroupEntity;

    @OneToMany(mappedBy = "foodRecommendEntity", cascade = CascadeType.REMOVE)
    private List<RecommendedFoodEntity> recommendedFoodEntities;

    @OneToMany(mappedBy = "foodRecommendEntity", cascade = CascadeType.REMOVE)
    private List<EatenMukboEntity> eatenMukboEntities;


    public static FoodRecommendEntity create(MukgroupEntity mukgroup,
                                             List<RecommendedFoodEntity> recommendedFoodEntities) {

        return FoodRecommendEntity.builder()
                .hasValue(false)
                .recommendDate(LocalDate.now())
                .mukgroupEntity(mukgroup)
                .recommendedFoodEntities(recommendedFoodEntities)
                .build();

    }
}
