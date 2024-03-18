package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.service.MukBTIService;
import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;
import com.spring.mmm.domain.mukgroups.controller.response.MukbosResponse;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MukboServiceImpl implements MukboService{
    private final MukboRepository mukboRepository;

    @Override
    public List<MukboResponse> findAllMukboResponsesByGroupId(Long groupId) {
        return mukboRepository.findAllMukboByGroupId(groupId).stream().map(item -> item.toResponse()).toList();
    }

    @Override
    public List<MukboResponse> findAllMukbotResponsesByGroupId(Long groupId) {
        return mukboRepository.findAllMukboByGroupId(groupId).stream().filter(item -> item.getType() == MukboType.MUKBOT).map(item -> item.toResponse()).toList();
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
