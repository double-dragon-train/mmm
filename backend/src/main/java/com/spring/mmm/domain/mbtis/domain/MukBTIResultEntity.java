package com.spring.mmm.domain.mbtis.domain;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
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

    public void modifyScore(Integer score){
        this.score = score;
    }
}
