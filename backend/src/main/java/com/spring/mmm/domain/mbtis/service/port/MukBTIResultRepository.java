package com.spring.mmm.domain.mbtis.service.port;

import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;

import java.util.List;

public interface MukBTIResultRepository {
    List<MukBTIResultEntity> findAllMukBTIResultByMukboId(Long mukboId);
}
