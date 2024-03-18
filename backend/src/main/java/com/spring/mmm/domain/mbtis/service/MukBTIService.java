package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;

import java.util.List;

public interface MukBTIService {
    List<MukBTIEntity> findAllMukBTIByMukboId(Long mukboId);
}
