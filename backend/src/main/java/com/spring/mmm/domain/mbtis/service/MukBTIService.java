package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResponse;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResult;
import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;

import java.util.List;

public interface MukBTIService {
    List<MukBTIQuestionEntity> findAllMukBTIQuestion();

    MukBTIResult calcMBTI(MukBTICalcRequest mukBTICalcRequest);

    void save(UserEntity user, String key);

    MukBTIResponse getMukBTI(UserEntity user);
}
