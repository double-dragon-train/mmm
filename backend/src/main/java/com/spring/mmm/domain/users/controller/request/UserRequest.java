package com.spring.mmm.domain.users.controller.request;

import lombok.*;

public class UserRequest {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Create {

        private String email;
        private String nickname;
        private String password;
        // private String password_confirm;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Modify {

        private String email;
        private String nickname;
        private String password;
        // private String password_confirm;
    }
}
