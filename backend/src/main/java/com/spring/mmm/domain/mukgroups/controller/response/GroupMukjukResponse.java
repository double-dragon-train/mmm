package com.spring.mmm.domain.mukgroups.controller.response;

import com.spring.mmm.domain.mukgroups.domain.Badge;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupMukjukResponse {
    private Long titleMukjukId;
    private List<Badge> badges;
}
