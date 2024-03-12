package com.spring.mmm.domain.mukgroups.controller.request;

import com.spring.mmm.domain.mbtis.controller.response.MBTI;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukbotModifyRequest {
    private String name;
    private MBTI mbti;
}
