package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukjuks.infra.MukGroupMukJukEntity;
import com.spring.mmm.domain.mukjuks.infra.MukjukEntity;
import com.spring.mmm.domain.muklogs.infra.MukLogEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mukgroup")
@Entity
public class MukGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mukgroup_id")
    private Long mukgroupId;

    @Column(name = "name")
    private String name;

    @Column(name = "image_src")
    private String imageSrc;

    @Column(name = "is_solo")
    private Boolean isSolo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukjuk_id")
    private MukjukEntity mukjukEntity;

    @OneToMany(mappedBy = "mukGroupEntity")
    private List<MukboEntity> mukboEntities;

    @OneToMany(mappedBy = "mukGroupEntity")
    private List<MukLogEntity> mukLogEntities;

    @OneToMany(mappedBy = "mukGroupEntity")
    private List<MukGroupMukJukEntity> mukGroupMukJukEntities;
}
