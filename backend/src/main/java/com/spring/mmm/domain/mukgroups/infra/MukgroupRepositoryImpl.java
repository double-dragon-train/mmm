package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.Mukgroup;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MukgroupRepositoryImpl implements MukgroupRepository {
    private final MukgroupJpaRepository mukgroupJpaRepository;
    
    @Override
    public Mukgroup save(MukgroupEntity mukgroupEntity) {
        return mukgroupJpaRepository.save(mukgroupEntity).to();
    }

    @Override
    public Mukgroup findByMukgroupId(Long mukgroupId) {
        return mukgroupJpaRepository.findByMukgroupId(mukgroupId).to();
    }

    @Override
    public void delete(MukgroupEntity mukgroupEntity) {
        mukgroupJpaRepository.delete(mukgroupEntity);
    }
}
