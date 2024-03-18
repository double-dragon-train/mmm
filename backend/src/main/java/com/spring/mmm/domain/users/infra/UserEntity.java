package com.spring.mmm.domain.users.infra;

import com.spring.mmm.domain.mbtis.infra.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.infra.MukboEntity;
import com.spring.mmm.domain.users.domain.User;
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

    public static UserEntity from(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .build();
    }

    public User to() {
        return User.builder()
                .id(this.id)
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .build();
    }

    public static UserEntity userEntityOnlyWithId(Long id) {
        return UserEntity.builder().id(id).build();
    }

}
