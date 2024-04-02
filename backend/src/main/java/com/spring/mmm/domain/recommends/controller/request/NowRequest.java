package com.spring.mmm.domain.recommends.controller.request;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NowRequest {
    private List<Long> nowMukbos;
}
