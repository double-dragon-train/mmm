package com.spring.mmm.domain.users.controller;

import com.spring.mmm.common.config.jwt.JwtProvider;
import com.spring.mmm.domain.users.controller.request.*;
import com.spring.mmm.domain.users.controller.response.TokenResponse;
import com.spring.mmm.domain.users.controller.response.UserEmailResponse;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.UserEmailSendService;
import com.spring.mmm.domain.users.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
    private final UserEmailSendService userEmailSendService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody UserJoinRequest userJoinRequest) {

        userService.join(userJoinRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{nickname}")
    public Boolean nicknameVerify(@PathVariable String nickname) {

        return userService.nicknameVerify(nickname);
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

    @PostMapping ("/email/verification-request")
    public String mailSend(@RequestBody @Valid UserEmailRequest userEmailRequest){

        if (userService.isAuthenticated()) {
            throw new UserException(UserErrorCode.IS_AUTHENTICATED);
        }

        return userEmailSendService.joinEmail(userEmailRequest.getEmail());
    }
    @PostMapping("/email/verification")
    public String authCheck(@RequestBody @Valid UserEmailCheckRequest userEmailCheckRequest){

        Boolean checked=userEmailSendService.checkAuthNum(
                userEmailCheckRequest.getEmail(),
                userEmailCheckRequest.getAuthNum()
        );
        if(checked){
            return "ok";
        }
        else{
            throw new UserException(UserErrorCode.CODE_NOT_SAME_ERROR);
        }
    }

}
