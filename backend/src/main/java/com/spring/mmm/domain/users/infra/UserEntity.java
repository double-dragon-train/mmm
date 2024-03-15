package com.spring.mmm.domain.users.infra;

import com.spring.mmm.domain.mbtis.infra.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String nickname;
    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
    private List<MukboEntity> mukboEntities;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
    private List<MukBTIResultEntity> mukBTIResultEntities;

}
