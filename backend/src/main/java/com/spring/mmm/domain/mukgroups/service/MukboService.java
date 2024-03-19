package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;

import java.util.List;

public interface MukboService {

    List<MukboResponse> findAllMukboResponsesByGroupId(Long groupId);

    List<MukboResponse> findAllMukbotResponsesByGroupId(Long groupId);

    void modifyMukbo(Long mukbotId, String name);

    void deleteMukbo(Long mukbotId);
}
