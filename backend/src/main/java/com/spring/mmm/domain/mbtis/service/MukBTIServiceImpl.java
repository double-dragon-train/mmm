package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MukBTIServiceImpl implements MukBTIService{
    private final MukBTIRepository mukBTIRepository;

    public List<MukBTIEntity> findAllMukBTIByMukboId(Long mukboId){
        return mukBTIRepository.findAllMukBTIByMukboId(mukboId);
    }
}
