package com.spring.mmm.domain.users.service;

import com.spring.mmm.domain.users.controller.request.UserJoinRequest;
import com.spring.mmm.domain.users.controller.request.UserLoginRequest;
import com.spring.mmm.domain.users.controller.request.UserModifyRequest;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    void join(UserJoinRequest UserJoinRequest);

    boolean nickname_verify(String nickname);

    void modify(UserDetailsImpl user, UserModifyRequest userModifyRequest);

    Optional<UserEntity> login(UserLoginRequest userLoginRequest);

    void logout(String accessToken, String username);

}
