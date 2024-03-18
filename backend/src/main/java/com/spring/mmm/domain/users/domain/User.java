package com.spring.mmm.domain.users.domain;

import com.spring.mmm.common.event.Events;
import com.spring.mmm.domain.users.controller.request.JoinRequest;
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

    public static User create(JoinRequest joinRequest, String encodedPW) {

        return User.builder()
                .email(joinRequest.getEmail())
                .password(encodedPW)
                .nickname(joinRequest.getNickname())
                .build();
    }


    public void deleteUser() {

        Events.raise(UserDeletedEvent.create(this.id));
    }
}
