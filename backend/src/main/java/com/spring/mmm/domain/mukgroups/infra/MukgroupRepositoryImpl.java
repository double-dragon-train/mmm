package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukboErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukboException;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MukgroupRepositoryImpl implements MukgroupRepository {
    private final MukgroupJpaRepository mukgroupJpaRepository;
    
    @Override
    public MukgroupEntity save(MukgroupEntity mukgroupEntity) {
        return mukgroupJpaRepository.save(mukgroupEntity);
    }

    @Override
    public Optional<MukgroupEntity> findByMukgroupId(Long mukgroupId) {
        return mukgroupJpaRepository.findByMukgroupId(mukgroupId);
    }

    @Override
    public void delete(MukgroupEntity mukgroupEntity) {
        mukgroupJpaRepository.delete(mukgroupEntity);
    }

    @Override
    public Integer countAllMukboByMukgroupId(Long mukgroupId) {
        return mukgroupJpaRepository.countAllMukboByMukgroupId(mukgroupId);
    }
}
