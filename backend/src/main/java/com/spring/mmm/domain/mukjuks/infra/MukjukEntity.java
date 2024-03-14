package com.spring.mmm.domain.mukjuks.infra;

import com.spring.mmm.domain.mukgroups.infra.MukgroupEntity;
import com.spring.mmm.domain.mukjuks.domain.Mukjuk;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mukjuk")
@Entity
public class MukjukEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mukjuk_id")
    private Long mukjukId;

    @Column(name = "name")
    private String name;

    @Column(name = "context")
    private String context;

    @Column(name = "image_src")
    private String imageSrc;

    @OneToMany(mappedBy = "mukjukEntity", cascade = CascadeType.REMOVE)
    private List<MukgroupMukjukEntity> mukGroupMukJukEntities;

    @OneToMany(mappedBy = "mukjukEntity")
    private List<MukgroupEntity> mukGroupEntities;

    public static MukjukEntity from(Mukjuk mukjuk){
        return MukjukEntity.builder()
                .mukjukId(mukjuk.getMukjukId())
                .name(mukjuk.getName())
                .context(mukjuk.getContext())
                .imageSrc(mukjuk.getImageSrc())
                .build();
    }

    public Mukjuk to(){
        return Mukjuk.builder()
                .mukjukId(this.mukjukId)
                .name(this.name)
                .context(this.context)
                .imageSrc(this.imageSrc)
                .build();
    }
}
