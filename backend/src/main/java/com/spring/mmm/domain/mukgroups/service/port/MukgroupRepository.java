package com.spring.mmm.domain.mukgroups.service.port;

import com.spring.mmm.domain.mukgroups.domain.Mukgroup;
import com.spring.mmm.domain.mukgroups.infra.MukgroupEntity;

public interface MukgroupRepository {
    Mukgroup save(MukgroupEntity mukgroupEntity);
    Mukgroup findByMukgroupId(Long mukgroupId);

    void delete(MukgroupEntity mukgroupEntity);
}
