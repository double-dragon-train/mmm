package com.spring.mmm.domain.mukgroups.domain;

import com.spring.mmm.domain.mbtis.infra.MukBTIEntity;
import com.spring.mmm.domain.mbtis.infra.MukBTIResultEntity;
import com.spring.mmm.domain.recommends.domain.EatenMukboEntity;
import com.spring.mmm.domain.users.infra.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mukbo")
@Entity
public class MukboEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mukbo_id")
    private Long mukboId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MukboType type;

    @OneToMany(mappedBy = "mukboEntity")
    private List<MukBTIResultEntity> mukBTIResultEntities;

    @OneToMany(mappedBy = "mukboEntity", cascade = CascadeType.REMOVE)
    private List<EatenMukboEntity> eatenMukboEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukgroup_id")
    private MukgroupEntity mukGroupEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbti_id")
    private MukBTIEntity mukBTIEntity;

    public static MukboEntity create(String name, MukboType mukboType, Long mukgroupId){
        return MukboEntity.builder()
                .name(name)
                .type(mukboType)
                .mukGroupEntity(MukgroupEntity.createWithOnlyId(mukgroupId))
                .build();
    }

    public MukboEntity modifyGroup(Long mukgroupId){
        return MukboEntity.builder()
                .name(this.name)
                .type(this.type)
                .mukGroupEntity(MukgroupEntity.createWithOnlyId(mukgroupId))
                .build();
    }

    public void modifyName(String name){
        this.name = name;
    }
}
