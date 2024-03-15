package com.spring.mmm.domain.mukgroups.controller.response;

import com.spring.mmm.domain.muklogs.domain.MukLogEntity;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukLogResponse {
    private List<MukLogEntity> contents;
    private Boolean hasNext;
}
