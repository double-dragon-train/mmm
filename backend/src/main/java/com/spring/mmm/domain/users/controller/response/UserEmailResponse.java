package com.spring.mmm.domain.users.controller.response;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(force = true)
public class UserEmailResponse {

    private final String email;

    public static UserEmailResponse of(String email){
        return new UserEmailResponse(email);
    }

}
