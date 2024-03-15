package com.spring.mmm.domain.mukgroups.domain;

import com.spring.mmm.domain.mukgroups.controller.response.MukgroupResponse;
import com.spring.mmm.domain.mukjuks.domain.MukgroupMukjukEntity;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import com.spring.mmm.domain.muklogs.domain.MukLogEntity;
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

    public static MukgroupEntity create(String name, Boolean isSolo){
        return MukgroupEntity.builder()
                .name(name)
                .isSolo(isSolo)
                .build();
    }

    public static MukgroupEntity createWithOnlyId(Long mukgroupId){
        return MukgroupEntity.builder()
                .mukgroupId(mukgroupId)
                .build();
    }

    public MukgroupEntity modifyMukgroupName(String name){
        return MukgroupEntity.builder()
                .mukgroupId(this.mukgroupId)
                .name(name)
                .isSolo(this.isSolo)
                .imageSrc(this.imageSrc)
                .build();
    }

    public MukgroupEntity modifyMukgroupImage(String ImageSrc){
        return MukgroupEntity.builder()
                .mukgroupId(this.mukgroupId)
                .name(this.name)
                .isSolo(this.isSolo)
                .imageSrc(imageSrc)
                .build();
    }

    public MukgroupResponse createMukgroupResponse(){
        return MukgroupResponse.builder()
                .mukgroupId(this.mukgroupId)
                .name(this.name)
                .isSolo(this.isSolo)
                .imageSrc(this.imageSrc)
                .build();
    }
}
