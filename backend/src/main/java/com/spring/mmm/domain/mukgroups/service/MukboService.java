package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;

import java.util.List;

public interface MukboService {

    List<MukboResponse> findAllMukboResponsesByGroupId(Long groupId);

    List<MukboResponse> findAllMukbotResponsesByGroupId(Long groupId);

    void inviteMukbo(UserDetailsImpl user, Long groupId, MukboInviteRequest mukboInviteRequest);

    void modifyMukbot(UserDetailsImpl user, Long mukbotId, MBTI mbti, String name);

    void modifyMokbo(Long userId, String name);

    void deleteMukbo(Long mukbotId);
}
