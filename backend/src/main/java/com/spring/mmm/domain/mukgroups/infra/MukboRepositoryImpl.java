package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MukboRepositoryImpl implements MukboRepository {
    private final MukboJpaRepository mukboJpaRepository;
    @Override
    public MukboEntity save(MukboEntity mukboEntity) {
        return mukboJpaRepository.save(mukboEntity);
    }

    @Override
    public MukboEntity findByUserId(Long userId) {
        return mukboJpaRepository.findByUserId(userId);
    }

    @Override
    public List<MukboEntity> findAllMukboByGroupId(Long groupId) {
        return mukboJpaRepository.findAllMukboByGroupId(groupId);
    }

    @Override
    public MukboEntity findByMukboId(Long mukboId) {
        return mukboJpaRepository.findByMukboId(mukboId);
    }

    @Override
    public void delete(MukboEntity mukboEntity) {
        mukboJpaRepository.delete(mukboEntity);
    }


}
