package com.spring.mmm.domain.users.controller;

import com.spring.mmm.domain.mbtis.controller.response.MBTI;
import com.spring.mmm.domain.users.controller.request.UserModifyRequest;
import com.spring.mmm.domain.users.controller.request.UserRegisterRequeset;
import com.spring.mmm.domain.users.controller.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("users")
public class UserMockController {

    @PostMapping("join")
    public ResponseEntity<Void> join(@RequestBody UserRegisterRequeset userRegisterRequeset){
        // 회원가입 로직 수행
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("email/verification-request")
    public ResponseEntity<Void> sendEmail(@RequestBody String email){
        // 1. Request 값 검증 수행
        // 2. 이메일 확인 코드 발송
        return ResponseEntity.ok().build();
    }

    @PostMapping("email/verification")
    public ResponseEntity<Void> verifyEmail(@RequestBody String email, @RequestBody String code){
        // 1. Request 값 검증 수행
        // 2. 이메일 확인 코드 검증
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Void> nicknameDupCheck(@RequestParam("nickname") String nickname){
        // 1. Request 값 검증 수행
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody String email, @RequestBody String password){
        // 1. Request 값 검증 수행
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> signout(@PathVariable Long userId){
        // 1. Request 값 검증 수행
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> modify(@RequestBody UserModifyRequest userModifyRequest){
        // 1. Request 값 검증 수행
        // 2. 닉네임/비밀번호 수정
        return ResponseEntity.ok().build();
    }

    @GetMapping("{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable String email){
        // 1. Request 값 검증 수행
        // 2. 유저 검색
        // 3. 결과 반환
        UserResponse userResponse = UserResponse.builder()
                .userId(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy1")
                .build();
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("mbti")
    public ResponseEntity<MBTI> getMBTI(){
        // 1. mbti검색
        MBTI mbti = MBTI.builder()
                .EI(20)
                .NS(100)
                .TF(0)
                .JP(80)
                .Mint(100)
                .Pine(100)
                .Die(0)
                .build();
        return ResponseEntity.ok(mbti);
    }


}
