package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.service.MukBTIService;
import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
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
        return mukboRepository.findAllMukboByGroupId(groupId)
                .stream()
                .map(item -> item.toResponse())
                .toList();
    }

    @Override
    public List<MukboResponse> findAllMukbotResponsesByGroupId(Long groupId) {
        return mukboRepository.findAllMukboByGroupId(groupId)
                .stream()
                .filter(item -> item.getType() == MukboType.MUKBOT)
                .map(item -> item.toResponse())
                .toList();
    }

    @Override
    public void inviteMukbo(MukboInviteRequest mukboInviteRequest) {
        // 먹봇을 먹보로 교체해야 한다.
        // 해당하는 ID의 먹보를 제거한다.
        // 회원과 연결된 먹보를 가져와 이메일/닉으로 등록한다.
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
