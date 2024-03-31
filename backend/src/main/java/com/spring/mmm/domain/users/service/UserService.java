package com.spring.mmm.domain.users.service;

import com.spring.mmm.domain.users.controller.request.UserJoinRequest;
import com.spring.mmm.domain.users.controller.request.UserLoginRequest;
import com.spring.mmm.domain.users.controller.request.UserModifyRequest;
import com.spring.mmm.domain.users.controller.request.UserReissueTokenRequest;
import com.spring.mmm.domain.users.controller.response.TokenResponse;
import com.spring.mmm.domain.users.controller.response.UserInfoResponse;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    void join(UserJoinRequest UserJoinRequest);

    boolean nicknameVerify(String nickname);

    void modify(UserDetailsImpl user, UserModifyRequest userModifyRequest);

    Optional<UserEntity> login(UserLoginRequest userLoginRequest);

    UserInfoResponse getUserInfo(String jwtToken);

    UserInfoResponse getUserInfoByEmail(String email);

    TokenResponse getToken(UserReissueTokenRequest request);

    void logout(String accessToken, String username);

    boolean isAuthenticated();

}
