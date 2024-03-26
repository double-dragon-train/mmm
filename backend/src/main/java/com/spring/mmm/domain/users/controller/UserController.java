package com.spring.mmm.domain.users.controller;

import com.spring.mmm.common.config.RedisDao;
import com.spring.mmm.common.config.jwt.JwtProvider;
import com.spring.mmm.domain.mbtis.controller.request.MukBTIRequest;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResponse;
import com.spring.mmm.domain.mbtis.service.MukBTIService;
import com.spring.mmm.domain.users.controller.request.*;
import com.spring.mmm.domain.users.controller.response.TokenResponse;
import com.spring.mmm.domain.users.controller.response.UserInfoResponse;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.UserEmailSendService;
import com.spring.mmm.domain.users.service.UserService;
import com.spring.mmm.domain.users.service.port.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final UserEmailSendService userEmailSendService;
    private final MukBTIService mukBTIService;
    private final RedisDao redisDao;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody UserJoinRequest userJoinRequest) {

        userService.join(userJoinRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<Boolean> nicknameVerify(@PathVariable String nickname) {

        return ResponseEntity.ok(userService.nicknameVerify(nickname));
    }

    @PutMapping
    public ResponseEntity<Void> modify(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody UserModifyRequest userModifyRequest) {

        userService.modify(user, userModifyRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        userService.login(userLoginRequest);
        TokenResponse token = jwtProvider.createTokenByLogin(userLoginRequest.getEmail());
        response.addHeader(jwtProvider.AUTHORIZATION_HEADER, token.getAccessToken());
        return ResponseEntity.ok(token);
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
        userService.logout(jwtProvider.resolveToken(request),userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo(@RequestHeader("Authorization") String token) {

        String jwtToken = token.substring(7);

        String email = jwtProvider.getUserInfoFromToken(jwtToken);
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        String nickname = user.getNickname();

        UserInfoResponse userInfoResponse = UserInfoResponse.of(email, nickname);

        return ResponseEntity.ok(userInfoResponse);
    }

    @GetMapping("/reissue")
    public ResponseEntity<TokenResponse> getToken(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserReissueTokenRequest request) {
        String email = userDetails.getUsername();
        String rtk = request.getRefreshToken();
        TokenResponse newToken = jwtProvider.reissueAtk(email, rtk);

        return ResponseEntity.ok(newToken);
    }

    @PostMapping ("/email/verification-request")
    public ResponseEntity<Void> mailSend(@RequestBody @Valid UserEmailRequest userEmailRequest){

        if (userService.isAuthenticated()) {
            throw new UserException(UserErrorCode.IS_AUTHENTICATED);
        }
        log.debug("유저 이메일 : {}", userEmailRequest.getEmail());

        String authNum = userEmailSendService.joinEmail(userEmailRequest.getEmail());
        log.debug("인증번호 : {}", authNum);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/email/verification")
    public ResponseEntity<Void> authCheck(@RequestBody @Valid UserEmailCheckRequest userEmailCheckRequest){

        Boolean checked=userEmailSendService.checkAuthNum(
                userEmailCheckRequest.getEmail(),
                userEmailCheckRequest.getAuthNum()
        );
        if(checked){
            log.debug("인증 완료");
            return ResponseEntity.ok().build();
        }
        else{
            throw new UserException(UserErrorCode.CODE_NOT_SAME_ERROR);
        }
    }

    @PostMapping("/mbti")
    public ResponseEntity<MukBTIResponse> saveMukBTI(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody MukBTIRequest mukBTIRequest
            ){
        mukBTIService.save(userDetails.getUser(), mukBTIRequest.getKey());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mbti")
    public ResponseEntity<MukBTIResponse> findMukBTIResult(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return ResponseEntity.ok(mukBTIService.getMukBTI(userDetails.getUser()));
    }

}
