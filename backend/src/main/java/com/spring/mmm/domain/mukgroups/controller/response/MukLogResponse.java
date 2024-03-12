package com.spring.mmm.domain.mukgroups.controller.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukLogResponse {
    List<MukLog> contents;
    Boolean hasNext;
}
