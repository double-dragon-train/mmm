package com.spring.mmm.domain.mukgroups.service.port;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;

public interface MukboRepository {
    MukboEntity save(MukboEntity mukboEntity);

    MukboEntity findByUserId(Long userId);
}
