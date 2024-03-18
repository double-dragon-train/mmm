package com.spring.mmm.domain.users.service;

import com.spring.mmm.domain.users.controller.request.JoinRequest;
import com.spring.mmm.domain.users.controller.request.LoginRequest;
import com.spring.mmm.domain.users.controller.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    void join(JoinRequest joinRequest);

    UserResponse login(LoginRequest loginRequest);

    ResponseEntity logout(String accessToken, String username);

}
