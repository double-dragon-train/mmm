package com.spring.mmm.domain.mukgroups.service.port;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;

import java.util.List;
import java.util.Optional;

public interface MukboRepository {
    MukboEntity save(MukboEntity mukboEntity);

    MukboEntity findByUserId(Long userId);

    List<MukboEntity> findAllMukboByGroupId(Long groupId);

    MukboEntity findByMukboId(Long mukboId);

    void delete(MukboEntity mukboEntity);
}
