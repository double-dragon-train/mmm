package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.recommends.infra.FoodMBTIEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mbti")
@Entity
public class MukBTIEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mbti_id")
    private Integer mukbtiId;

    @Column(name = "mbti_name")
    @Enumerated(EnumType.STRING)
    private MukBTIType type;

    @OneToMany(mappedBy = "mukBTIEntity")
    private List<FoodMBTIEntity> foodMBTIEntities;

    @OneToMany(mappedBy = "mukBTIEntity")
    private List<MukBTIQuestionEntity> mukBTIQuestionEntities;

    @OneToMany(mappedBy = "mukBTIEntity")
    private List<MukBTIResultEntity> mukBTIResultEntities;
}
