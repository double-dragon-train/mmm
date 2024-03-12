package com.spring.mmm.domain.users.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRegisterRequeset {

    String email;

    String password;

    String passwordConfirm;

    String nickname;

}
