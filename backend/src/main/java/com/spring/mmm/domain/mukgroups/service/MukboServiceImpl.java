package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MukboServiceImpl implements MukboService{
    private final MukboRepository mukboRepository;

    @Override
    public List<MukboEntity> findAllMukboByGroupId(Long groupId) {
        return mukboRepository.findAllMukboByGroupId(groupId);
    }

    @Override
    public List<MukboEntity> findAllMukbotByGroupId(Long groupId) {
        return mukboRepository.findAllMukbotByGroupId(groupId);
    }

    @Override
    public void modifyMukbo(Long mukboId, String name) {
        mukboRepository.findByMukboId(mukboId).modifyName(name);
    }

    @Override
    public void deleteMukbo(Long mukboId) {
        mukboRepository.delete(mukboRepository.findByMukboId(mukboId));
    }
}
