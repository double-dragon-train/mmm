package com.spring.mmm.domain.mukgroups.domain;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupImage {
    private String contentType;
    private String originalFilename;
    private String filePath;
    private String size;
}
