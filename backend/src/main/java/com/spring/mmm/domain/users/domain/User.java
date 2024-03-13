package com.spring.mmm.domain.users.domain;

import com.spring.mmm.common.event.Events;
import com.spring.mmm.domain.users.controller.request.UserRequest;
import com.spring.mmm.domain.users.event.UserDeletedEvent;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String email;
    private String password;
    private String nickname;

    public static User create(UserRequest.Create userRequest) {

        return User.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .nickname(userRequest.getNickname())
                .build();
    }

    public void modifyUserInfo(UserRequest.Modify dto) {

        boolean emptyCheckFlag = true;

        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
            emptyCheckFlag = false;
        }
        if (dto.getPassword() != null) {
            this.password = dto.getPassword();
            emptyCheckFlag = false;
        }
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
            emptyCheckFlag = false;
        }

        if (emptyCheckFlag) {
            throw new UserException(UserErrorCode.NO_INPUT_FOR_MODIFY_USER_INFO);
        }
    }

    public void deleteUser() {

        Events.raise(UserDeletedEvent.create(this.id));
    }
}
