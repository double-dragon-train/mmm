package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.Mukgroup;
import com.spring.mmm.domain.mukjuks.infra.MukgroupMukjukEntity;
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
public class MukgroupEntity {

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

    @OneToMany(mappedBy = "mukGroupEntity", cascade = CascadeType.REMOVE)
    private List<MukboEntity> mukboEntities;

    @OneToMany(mappedBy = "mukGroupEntity", cascade = CascadeType.REMOVE)
    private List<MukLogEntity> mukLogEntities;

    @OneToMany(mappedBy = "mukGroupEntity", cascade = CascadeType.REMOVE)
    private List<MukgroupMukjukEntity> mukGroupMukJukEntities;

    public static MukgroupEntity fromSolo(String name){
        return MukgroupEntity.builder()
                .name(name)
                .isSolo(Boolean.TRUE)
                .build();
    }

    public static MukgroupEntity from(Mukgroup mukgroup){
        return MukgroupEntity.builder()
                .mukgroupId((mukgroup.getMukgroupId()))
                .name(mukgroup.getName())
                .imageSrc(mukgroup.getImageSrc())
                .isSolo(mukgroup.getIsSolo())
                .build();
    }

    public Mukgroup to(){
        return Mukgroup.builder()
                .mukgroupId(this.mukgroupId)
                .name(this.name)
                .imageSrc(this.imageSrc)
                .isSolo(this.isSolo)
                .mukjukId(this.mukjukEntity.getMukjukId())
                .build();
    }
}
