package com.spring.mmm.domain.mukjuks.infra;

import com.spring.mmm.domain.mukgroups.infra.MukGroupEntity;
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

    @OneToMany(mappedBy = "mukjukEntity")
    private List<MukGroupMukJukEntity> mukGroupMukJukEntities;

    @OneToMany(mappedBy = "mukjukEntity")
    private List<MukGroupEntity> mukGroupEntities;
}
