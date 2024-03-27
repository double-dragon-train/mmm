package com.spring.mmm.domain.users.service;

import com.spring.mmm.common.config.RedisDao;
import com.spring.mmm.common.config.jwt.JwtProvider;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.users.controller.request.UserJoinRequest;
import com.spring.mmm.domain.users.controller.request.UserLoginRequest;
import com.spring.mmm.domain.users.controller.request.UserModifyRequest;
import com.spring.mmm.domain.users.controller.request.UserReissueTokenRequest;
import com.spring.mmm.domain.users.controller.response.TokenResponse;
import com.spring.mmm.domain.users.controller.response.UserInfoResponse;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final MukboRepository mukboRepository;
    private final MukgroupRepository mukgroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisDao redisDao;


    @Override
    @Transactional
    public void join(UserJoinRequest userJoinRequest) {

        String email = userJoinRequest.getEmail();
        String nickname = userJoinRequest.getNickname();
        String password = userJoinRequest.getPassword();
        String encodedPW = passwordEncoder.encode(password);

        boolean isEmailExist = userRepository.existsByEmail(email);
        boolean isNicknameExist = userRepository.existsByNickname(nickname);

        if (isEmailExist | isNicknameExist) {
            return;
        }

        MukgroupEntity mukgroupEntity = mukgroupRepository.save(MukgroupEntity.create(nickname, Boolean.TRUE));
        MukboEntity mukboEntity = mukboRepository.save(MukboEntity.create(nickname, MukboType.HUMAN, mukgroupEntity.getMukgroupId()));
        UserEntity user = UserEntity.create(userJoinRequest, encodedPW, mukboEntity);
        userRepository.create(user);
    }

    @Override
    @Transactional
    public boolean nicknameVerify(String nickname) {

        boolean isNicknameExist = userRepository.existsByNickname(nickname);

        if (isNicknameExist) {
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public void modify(UserDetailsImpl user, UserModifyRequest userModifyRequest) {
        String email = user.getEmail();
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new UserException(UserErrorCode.INVALID_USER)
        );

        String nickname = userEntity.getNickname();

        if (userModifyRequest.getNickname() != null) {
            nickname = userModifyRequest.getNickname();
            boolean isNicknameExist = userRepository.existsByNickname(nickname);

            if (isNicknameExist) {
                return;
            }
        }

        String password = userEntity.getPassword();

        if (userModifyRequest.getNewPassword() != null) {
            password = passwordEncoder.encode(userModifyRequest.getNewPassword());
        }

        userEntity.modify(nickname, password);
    }


    @Override
    @Transactional
    public Optional<UserEntity> login(UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserException(UserErrorCode.INVALID_USER)
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_USER);
        }
        return Optional.of(user);
    }

    @Override
    @Transactional
    public UserInfoResponse getUserInfo(String jwtToken) {

        String email = jwtProvider.getUserInfoFromToken(jwtToken);
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        String nickname = user.getNickname();

        return UserInfoResponse.of(email, nickname);
    }

    @Override
    public TokenResponse getToken(UserDetailsImpl userDetails, UserReissueTokenRequest request) {

        String email = userDetails.getUsername();
        String rtk = request.getRefreshToken();

        return jwtProvider.reissueAtk(email, rtk);
    }


    @Override
    @Transactional
    public void logout(String accessToken, String email) {
        if (redisDao.hasKey(email)) {
            redisDao.deleteRefreshToken(email);
        } else {
            throw new IllegalArgumentException("이미 로그아웃한 유저입니다.");
        }
    }

    @Override
    @Transactional
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !authentication.getPrincipal().equals("anonymousUser");
    }

}
