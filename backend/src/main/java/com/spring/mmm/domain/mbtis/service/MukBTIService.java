package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.domain.mbtis.controller.request.MBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.MBTIResult;
import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;

import java.util.List;

public interface MukBTIService {
    List<MukBTIQuestionEntity> findAllMukBTIQuestion();

    MBTIResult calcMBTI(MBTICalcRequest mbtiCalcRequest, Long userId);
}
