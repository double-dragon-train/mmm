package com.spring.mmm.domain.users.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRegisterRequeset {

    private String email;

    private String password;

    private String passwordConfirm;

    private String nickname;

}
