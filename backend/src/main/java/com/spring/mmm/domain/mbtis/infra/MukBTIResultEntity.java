package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIResult;
import com.spring.mmm.domain.mukgroups.infra.MukboEntity;
import com.spring.mmm.domain.users.infra.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "result")
@Entity
public class MukBTIResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer resultId;

    @Column(name = "score")
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbti_id")
    private MukBTIEntity mukBTIEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukbo_id")
    private MukboEntity mukboEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public static MukBTIResultEntity from(MukBTIResult mukBTIResult){
        return MukBTIResultEntity.builder()
                .resultId(mukBTIResult.getResultId())
                .score(mukBTIResult.getScore())
                .build();
    }

    public MukBTIResult to(){
        return MukBTIResult.builder()
                .resultId(this.resultId)
                .score(this.score)
                .mukbtiId(this.mukBTIEntity.getMukbtiId())
                .mukboId(this.mukboEntity.getMukboId())
                .userId(this.userEntity.getId())
                .build();
    }
}
