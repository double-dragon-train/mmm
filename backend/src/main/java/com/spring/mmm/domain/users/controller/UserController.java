package com.spring.mmm.domain.users.controller;

import com.spring.mmm.common.config.jwt.JwtProvider;
import com.spring.mmm.domain.users.controller.request.JoinRequest;
import com.spring.mmm.domain.users.controller.request.LoginRequest;
import com.spring.mmm.domain.users.controller.response.TokenResponse;
import com.spring.mmm.domain.users.controller.response.UserResponse;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest joinRequest) {

        userService.join(joinRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        UserResponse user = userService.login(loginRequest);
        TokenResponse token = jwtProvider.createTokenByLogin(user.getEmail(), user.getNickname());//atk, rtk 생성
        response.addHeader(jwtProvider.AUTHORIZATION_HEADER, token.getAccessToken());// 헤더에 에세스 토큰만 싣기
        return token;
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
        String accessToken = jwtProvider.resolveToken(request);
        return userService.logout(accessToken,userDetails.getUsername());//username = email

    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserResponse> getUserInfo(@RequestHeader("Authorization") String token) {
        // 'Authorization' 헤더에서 토큰을 추출합니다.
        String jwtToken = token.substring(7); // "Bearer " 접두어 제거

        // JwtProvider를 사용하여 토큰에서 사용자 정보를 추출합니다.
        Claims claims = jwtProvider.getUserInfoFromToken(jwtToken);

        // 필요한 사용자 정보를 추출합니다.
        String email = claims.get("email", String.class);
        String nickname = claims.get("nickname", String.class);
        System.out.println("user email : " + email);
        System.out.println("nickname : " + nickname);
        UserResponse userResponse = UserResponse.of(email, nickname);

        // UserInfo 객체를 반환합니다.
        return ResponseEntity.ok(userResponse);
    }

}
