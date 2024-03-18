package com.spring.mmm.domain.mbtis.service.port;

import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;

import java.util.List;

public interface MukBTIRepository {
    List<MukBTIEntity> findAllMukBTIByMukboId(Long mukboId);
}
