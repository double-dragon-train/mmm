package com.spring.mmm.domain.users.controller;

import com.spring.mmm.common.config.jwt.JwtProvider;
import com.spring.mmm.domain.users.controller.request.UserJoinRequest;
import com.spring.mmm.domain.users.controller.request.UserLoginRequest;
import com.spring.mmm.domain.users.controller.request.UserModifyRequest;
import com.spring.mmm.domain.users.controller.response.TokenResponse;
import com.spring.mmm.domain.users.controller.response.UserEmailResponse;
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

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody UserJoinRequest userJoinRequest) {

        userService.join(userJoinRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{nickname}")
    public Boolean nickname_verify(@PathVariable String nickname) {

        return userService.nickname_verify(nickname);
    }

    @PutMapping("/")
    public ResponseEntity<Void> modify(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody UserModifyRequest userModifyRequest) {

        userService.modify(user, userModifyRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        userService.login(userLoginRequest);
        TokenResponse token = jwtProvider.createTokenByLogin(userLoginRequest.getEmail());
        response.addHeader(jwtProvider.AUTHORIZATION_HEADER, token.getAccessToken());
        return token;
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
        userService.logout(jwtProvider.resolveToken(request),userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<UserEmailResponse> getUserInfo(@RequestHeader("Authorization") String token) {

        String jwtToken = token.substring(7);

        String email = jwtProvider.getUserInfoFromToken(jwtToken);

        UserEmailResponse userEmailResponse = UserEmailResponse.of(email);

        return ResponseEntity.ok(userEmailResponse);
    }

}
