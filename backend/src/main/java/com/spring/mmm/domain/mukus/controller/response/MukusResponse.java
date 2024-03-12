package com.spring.mmm.domain.mukus.controller.response;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukusResponse {
    private Long mukusId;
    private Date date;
    private String colorcode;
    private String category;
}
