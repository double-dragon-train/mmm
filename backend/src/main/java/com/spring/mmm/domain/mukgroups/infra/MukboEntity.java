package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mbtis.infra.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.domain.Mukbo;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.recommends.infra.EatenMukboEntity;
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

    public static MukboEntity from(Mukbo mukbo){
        return MukboEntity.builder()
                .mukboId(mukbo.getMukboId())
                .name(mukbo.getName())
                .type(mukbo.getType())
                .build();
    }

    public Mukbo to(){
        return Mukbo.builder()
                .mukboId(this.mukboId)
                .name(this.name)
                .type(this.type)
                .mukgroupId(this.mukGroupEntity.getMukgroupId())
                .userId(this.userEntity.getId())
                .build();
    }

    public Mukbo to(Long mukgroupId){
        return Mukbo.builder()
                .mukboId(this.mukboId)
                .name(this.name)
                .type(this.type)
                .mukgroupId(mukgroupId)
                .userId(this.userEntity.getId())
                .build();
    }
}
