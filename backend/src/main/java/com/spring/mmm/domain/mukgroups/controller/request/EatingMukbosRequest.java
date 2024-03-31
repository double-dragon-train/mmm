package com.spring.mmm.domain.mukgroups.controller.request;

import com.spring.mmm.domain.mukus.controller.response.Mukbo;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EatingMukbosRequest {
    @NotBlank(message = "아무도 식사 안하시나요?")
    private List<Mukbo> mukbosNow;

}

