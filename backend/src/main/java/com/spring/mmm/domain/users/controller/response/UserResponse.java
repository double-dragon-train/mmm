package com.spring.mmm.domain.users.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {
    private Long userId;
    private String email;
    private String nickname;
}
