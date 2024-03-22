package com.spring.mmm.domain.mukgroups.service.port;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;

import java.util.Optional;

public interface MukgroupRepository {
    MukgroupEntity save(MukgroupEntity mukgroupEntity);
    Optional<MukgroupEntity> findByMukgroupId(Long mukgroupId);
    void delete(MukgroupEntity mukgroupEntity);
    Integer countAllMukboByMukgroupId(Long mukgroupId);
}
