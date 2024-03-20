package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MukBTIResultRepositoryImpl implements MukBTIResultRepository {
    private final MukBTIResultJpaRepository mukBTIResultJpaRepository;
    @Override
    public List<MukBTIResultEntity> findAllMukBTIResultByMukboId(Long mukboId) {
        return mukBTIResultJpaRepository.findAllMukBTIResultByMukboId(mukboId);
    }
}
