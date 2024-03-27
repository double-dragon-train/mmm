package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.mbtis.exception.MukBTIErrorCode;
import com.spring.mmm.domain.mbtis.exception.MukBTIException;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MukBTIRepositoryImpl implements MukBTIRepository {
    private final MukBTIJpaRepository mukBTIJpaRepository;
    @Override
    public MukBTIEntity findMukBTIByMukBTIType(MukBTIType mukBTIType) {
        return mukBTIJpaRepository.findMukBTIByMukBTIType(mukBTIType)
                .orElseThrow(() -> new MukBTIException(MukBTIErrorCode.NOT_FOUND));
    }

    @Override
    public List<MukBTIEntity> findAllMukBTI() {
        return mukBTIJpaRepository.findAllMukBTI();
    }
}
