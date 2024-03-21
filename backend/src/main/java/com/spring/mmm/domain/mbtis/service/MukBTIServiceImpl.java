package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.domain.mbtis.controller.request.CalcInfo;
import com.spring.mmm.domain.mbtis.controller.request.MBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.MBTIResult;
import com.spring.mmm.domain.mbtis.domain.*;
import com.spring.mmm.domain.mbtis.service.port.MukBTIQuestionRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MukBTIServiceImpl implements MukBTIService{
    private final MukBTIQuestionRepository mukBTIQuestionRepository;
    private final RedisTemplate<Long, MBTI> redisTemplate;
    @Override
    public List<MukBTIQuestionEntity> findAllMukBTIQuestion() {
        return mukBTIQuestionRepository.findAllMukBTIQuestion();
    }

    @Override
    public MBTIResult calcMBTI(MBTICalcRequest mbtiCalcRequest, Long userId) {

        List<MukBTIQuestionEntity> questions = findAllMukBTIQuestion();

        int EI = 0, NS = 0, TF = 0, JP = 0, Mint = 0, Pine = 0, Die = 0;
        for(CalcInfo calcInfo : mbtiCalcRequest.getAnswers()){
            for(MukBTIQuestionEntity question : questions){
                if(question.getQuestionId() == calcInfo.getQuizId()) {
                    for (MukBTIAnswerEntity answer : question.getMukBTIAnswerEntities()) {
                        if (answer.getAnswerId() == calcInfo.getAnswerId()) {
                            switch (question.getMukBTIEntity().getType()){
                                case MukBTIType.EI -> EI += answer.getScore();
                                case MukBTIType.NS -> NS += answer.getScore();
                                case MukBTIType.TF -> TF += answer.getScore();
                                case MukBTIType.JP -> JP += answer.getScore();
                                case MukBTIType.MINT -> Mint += answer.getScore();
                                case MukBTIType.PINE -> Pine += answer.getScore();
                                case MukBTIType.DIE -> Die += answer.getScore();
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }

        MBTI mbti = MBTI.builder()
                .EI(EI)
                .NS(NS)
                .TF(TF)
                .JP(JP)
                .Mint(Mint)
                .Pine(Pine)
                .Die(Die)
                .build();



        return null;
    }
}
