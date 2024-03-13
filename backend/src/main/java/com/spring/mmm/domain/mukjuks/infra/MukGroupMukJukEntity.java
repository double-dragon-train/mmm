package com.spring.mmm.domain.mukjuks.infra;

import com.spring.mmm.domain.mukgroups.infra.MukGroupEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mukgroup_mukjuk")
@Entity
public class MukGroupMukJukEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mukgroup_mukjuk_id")
    private Long mukgorupMukjukId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukjuk_id")
    private MukjukEntity mukjukEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukgroup_id")
    private MukGroupEntity mukGroupEntity;
}
