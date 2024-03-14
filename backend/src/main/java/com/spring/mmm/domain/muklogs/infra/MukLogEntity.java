package com.spring.mmm.domain.muklogs.infra;

import com.spring.mmm.domain.mukgroups.infra.MukgroupEntity;
import com.spring.mmm.domain.muklogs.domain.MukLog;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "muklog")
@Entity
public class MukLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "muklog_id")
    private Long muklogId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukgroup_id")
    private MukgroupEntity mukGroupEntity;

    public static MukLogEntity from(MukLog mukLog){
        return MukLogEntity.builder()
                .muklogId(mukLog.getMuklogId())
                .createdAt(mukLog.getCreatedAt())
                .content(mukLog.getContent())
                .build();
    }

    public MukLog to(){
        return MukLog.builder()
                .muklogId(this.muklogId)
                .createdAt(this.createdAt)
                .content(this.content)
                .mukgroupId(this.mukGroupEntity.getMukgroupId())
                .build();
    }
}
