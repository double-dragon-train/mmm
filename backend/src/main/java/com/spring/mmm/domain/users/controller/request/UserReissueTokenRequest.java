package com.spring.mmm.domain.users.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class UserReissueTokenRequest {
    private final String refreshToken;

    public UserReissueTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
