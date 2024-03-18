package com.spring.mmm.domain.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mmm.common.config.CacheNames;
import com.spring.mmm.common.config.RedisDao;
import com.spring.mmm.common.config.jwt.JwtProvider;
import com.spring.mmm.domain.users.controller.request.JoinRequest;
import com.spring.mmm.domain.users.controller.request.LoginRequest;
import com.spring.mmm.domain.users.controller.response.UserResponse;
import com.spring.mmm.domain.users.domain.User;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisDao redisDao;


    @Override
    @Transactional
    public void join(JoinRequest joinRequest) {

        String email = joinRequest.getEmail();
        String nickname = joinRequest.getNickname();
        String password = joinRequest.getPassword();

        Boolean isExist = userRepository.existsByEmail(email);

        if (isExist) {
            return;
        }

        User user = User.create(joinRequest, passwordEncoder.encode(password));

        userRepository.create(user);

    }

    /**
     * 로그인 반환값으로 user를 userResponseDto 담아 반환하고  컨트롤러에서 반환된 객체를 이용하여 토큰 발행한다.
     */
//    @Cacheable(cacheNames = CacheNames.LOGINUSER, key = "'login'+ #p0.getEmail()", unless = "#result== null")

    @Override
    @Cacheable(cacheNames = CacheNames.LOGINUSER, key = "'login'+ #p0.getEmail()", unless = "#result== null")
    @Transactional
    public UserResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserException(UserErrorCode.INVALID_USER)
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_USER);
        }
        return new UserResponse().of(user.getEmail(), user.getNickname());// user객체를 dto에 담아서 반환
    }

    @CacheEvict(cacheNames = CacheNames.USERBYEMAIL, key = "'login'+#p1")
    @Transactional
    public ResponseEntity logout(String accessToken, String email) {
        // 레디스에 accessToken 사용못하도록 등록
        Long expiration = jwtProvider.getExpiration(accessToken);
        if (redisDao.hasKey(email)) {
            redisDao.deleteRefreshToken(email);
        } else {
            throw new IllegalArgumentException("이미 로그아웃한 유저입니다.");
        }
        return ResponseEntity.ok("로그아웃 완료");
    }

}
