package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MukgroupRepositoryImpl implements MukgroupRepository {
    private final MukgroupJpaRepository mukgroupJpaRepository;
    
    @Override
    public MukgroupEntity save(MukgroupEntity mukgroupEntity) {
        return mukgroupJpaRepository.save(mukgroupEntity);
    }

    @Override
    public MukgroupEntity findByMukgroupId(Long mukgroupId) {
        return mukgroupJpaRepository.findByMukgroupId(mukgroupId);
    }

    @Override
    public void delete(MukgroupEntity mukgroupEntity) {
        mukgroupJpaRepository.delete(mukgroupEntity);
    }
}
