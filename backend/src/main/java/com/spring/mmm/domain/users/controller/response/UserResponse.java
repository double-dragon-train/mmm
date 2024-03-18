package com.spring.mmm.domain.users.controller.response;

import com.spring.mmm.domain.users.infra.UserEntity;
import lombok.*;

@Getter
@NoArgsConstructor(force = true)
public class UserResponse {

    private final String email;
    private final String nickname;

    /**
     *  유저 생성자를 private로 외부에서 생성 할수 없도록 함
     * @param user
     */
    private UserResponse(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }


    public static UserResponse of(String email, String nickname){
        return new UserResponse(email, nickname);
    }


}
