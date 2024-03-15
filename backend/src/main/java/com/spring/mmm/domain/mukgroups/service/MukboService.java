package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;

import java.util.List;

public interface MukboService {
    List<MukboEntity> findAllMukboByGroupId(Long groupId);
    List<MukboEntity> findAllMukbotByGroupId(Long groupId);

    void modifyMukbo(Long mukbotId, String name);

    void deleteMukbo(Long mukbotId);
}
