package com.spring.mmm.domain.users.controller.request;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinRequest {

    private String email;
    private String nickname;
    private String password;

}
