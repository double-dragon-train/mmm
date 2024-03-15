package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTI;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.recommends.domain.FoodMBTIEntity;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukbo_id")
    private MukboEntity mukboEntity;

    @OneToMany(mappedBy = "mukBTIEntity", cascade = CascadeType.REMOVE)
    private List<FoodMBTIEntity> foodMBTIEntities;

    @OneToMany(mappedBy = "mukBTIEntity", cascade = CascadeType.REMOVE)
    private List<MukBTIQuestionEntity> mukBTIQuestionEntities;

    @OneToMany(mappedBy = "mukBTIEntity", cascade = CascadeType.REMOVE)
    private List<MukBTIResultEntity> mukBTIResultEntities;

    public static MukBTIEntity from(MukBTI mukBTI){
        return MukBTIEntity.builder()
                .mukbtiId(mukBTI.getMukbtiId())
                .type(mukBTI.getType())
                .build();
    }

    public MukBTI to(){
        return MukBTI.builder()
                .mukbtiId(this.mukbtiId)
                .type(this.type)
                .build();
    }
}
