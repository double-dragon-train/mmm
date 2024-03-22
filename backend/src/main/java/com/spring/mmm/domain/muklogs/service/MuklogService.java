package com.spring.mmm.domain.muklogs.service;

import com.spring.mmm.domain.muklogs.controller.response.MuklogsResponse;
import org.springframework.data.domain.Pageable;

public interface MuklogService {
    MuklogsResponse findAllMuklogByGroupId(Long groupId, Pageable pageable);

    void saveLog(Long mukgroupId, String content);
}
