package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.service.MukBTIService;
import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;
import com.spring.mmm.domain.mukgroups.controller.response.MukbosResponse;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MukboServiceImpl implements MukboService{
    private final MukboRepository mukboRepository;
    private final UserRepository userRepository;

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
    public void inviteMukbo(UserDetailsImpl user, Long groupId, MukboInviteRequest mukboInviteRequest) {
        // 먹봇을 먹보로 교체해야 한다.
        // 해당하는 ID의 먹보를 제거한다.
        // 회원과 연결된 먹보를 가져와 이메일/닉으로 등록한다.
        // 그룹아이디, 유저 그룹아이디 검증필요

        // 유저의 먹봇 가져온다.
        MukboEntity mukboEntity = mukboRepository.findByUserId(userRepository.findByEmail(mukboInviteRequest.getEmail())
                .orElseThrow(() -> new UserException(UserErrorCode.EMAIL_NOT_FOUND)).getId());

        mukboRepository.delete(mukboRepository.findByMukboId(mukboInviteRequest.getMukbotId()));

        mukboEntity.modifyName(mukboInviteRequest.getNickname());
        mukboEntity.modifyGroup(user.getUser().getMukboEntity().getMukgroupEntity().getMukgroupId());
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
