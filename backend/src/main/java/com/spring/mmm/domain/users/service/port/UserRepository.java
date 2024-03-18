package com.spring.mmm.domain.users.service.port;

import com.spring.mmm.domain.users.domain.User;
import com.spring.mmm.domain.users.infra.UserEntity;

import java.util.Optional;

public interface UserRepository {

    User create(User user);

    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}
