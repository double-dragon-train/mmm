package com.spring.mmm.domain.muklogs.infra;

import com.spring.mmm.domain.mukgroups.infra.MukGroupEntity;
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
    private MukGroupEntity mukGroupEntity;
}
