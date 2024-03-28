package com.spring.mmm.domain.mukjuks.service.port;

import com.spring.mmm.domain.mukjuks.controller.response.Badge;

import java.util.List;

public interface MukjukRepository {
    List<Badge> findAllBadges(Long groupId);
}
