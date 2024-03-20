package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;

import java.util.List;

public interface MukboService {

    List<MukboResponse> findAllMukboResponsesByGroupId(Long groupId);

    List<MukboResponse> findAllMukbotResponsesByGroupId(Long groupId);

    void inviteMukbo(MukboInviteRequest mukboInviteRequest);

    void modifyMukbo(Long mukbotId, String name);

    void deleteMukbo(Long mukbotId);
}
