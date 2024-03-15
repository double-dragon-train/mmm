package com.spring.mmm.domain.mukgroups.service.port;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;

public interface MukgroupRepository {
    MukgroupEntity save(MukgroupEntity mukgroupEntity);
    MukgroupEntity findByMukgroupId(Long mukgroupId);

    void delete(MukgroupEntity mukgroupEntity);
}
