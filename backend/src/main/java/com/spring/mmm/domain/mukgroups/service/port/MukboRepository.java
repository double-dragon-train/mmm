package com.spring.mmm.domain.mukgroups.service.port;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;

import java.util.List;

public interface MukboRepository {
    MukboEntity save(MukboEntity mukboEntity);

    MukboEntity findByUserId(Long userId);

    List<MukboEntity> findAllMukboByGroupId(Long groupId);

    List<MukboEntity> findAllMukbotByGroupId(Long groupId);

    MukboEntity findByMukboId(Long mukboId);

    void delete(MukboEntity mukboEntity);
}
