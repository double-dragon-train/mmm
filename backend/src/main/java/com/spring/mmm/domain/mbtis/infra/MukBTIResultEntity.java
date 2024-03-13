package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mukgroups.infra.MukboEntity;
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

}
