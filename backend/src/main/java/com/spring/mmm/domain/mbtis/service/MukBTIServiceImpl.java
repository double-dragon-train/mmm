package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.common.service.RedisRepository;
import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcInfo;
import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResponse;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResult;
import com.spring.mmm.domain.mbtis.domain.*;
import com.spring.mmm.domain.mbtis.exception.MukBTIErrorCode;
import com.spring.mmm.domain.mbtis.exception.MukBTIException;
import com.spring.mmm.domain.mbtis.service.port.MukBTIQuestionRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.users.infra.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MukBTIServiceImpl implements MukBTIService {
    private final MukBTIQuestionRepository mukBTIQuestionRepository;
    private final RedisRepository redisRepository;
    private final MukBTIResultRepository mukBTIResultRepository;
    private final MukBTIRepository mukBTIRepository;

    @Override
    public List<MukBTIQuestionEntity> findAllMukBTIQuestion() {
        return mukBTIQuestionRepository.findAllMukBTIQuestion();
    }

    @Override
    public MukBTIResult calcMBTI(MukBTICalcRequest mukBTICalcRequest) {
        for(MukBTICalcInfo mukBTICalcInfo : mukBTICalcRequest.getAnswers()){
            if(mukBTICalcInfo.getQuizId() == null || mukBTICalcInfo.getAnswerId() == null){
                throw new MukBTIException(MukBTIErrorCode.BAD_REQUEST);
            }
        }

        List<MukBTIQuestionEntity> questions = findAllMukBTIQuestion();

        int EI = 0, NS = 0, TF = 0, JP = 0, Mint = 0, Pine = 0, Die = 0;

        for(MukBTICalcInfo mukBTICalcInfo : mukBTICalcRequest.getAnswers()){
            MukBTIQuestionEntity question = matchQuestion(questions, mukBTICalcInfo.getQuizId());

            MukBTIAnswerEntity answer = matchAnswer(question.getMukBTIAnswerEntities(), mukBTICalcInfo.getAnswerId());

            switch (question.getMukBTIEntity().getType()) {
                case MukBTIType.EI -> EI += answer.getScore();
                case MukBTIType.NS -> NS += answer.getScore();
                case MukBTIType.TF -> TF += answer.getScore();
                case MukBTIType.JP -> JP += answer.getScore();
                case MukBTIType.MINT -> Mint += answer.getScore();
                case MukBTIType.PINE -> Pine += answer.getScore();
                case MukBTIType.DIE -> Die += answer.getScore();
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

        String key = UUID.randomUUID().toString();
        redisRepository.saveData(key, mbti);
        return MukBTIResult.builder().key(key).mbti(mbti).build();
    }

    @Override
    @Transactional
    public void save(UserEntity user, String key) {
        MBTI mbti = redisRepository.getData(key, MBTI.class)
                .orElseThrow(() -> new MukBTIException(MukBTIErrorCode.NOT_FOUND));

        MukboEntity mukboEntity = user.getMukboEntity();

        List<MukBTIResultEntity> results = new ArrayList<>();
        List<MukBTIEntity> mukBTIs = mukBTIRepository.findAllMukBTI();

        for(MukBTIEntity mukBTIEntity : mukBTIs){
            MukBTIResultEntity mukBTIResult = MukBTIResultEntity.createWithoutScore(mukBTIEntity, mukboEntity, user);
            switch (mukBTIEntity.getType()){
                case EI -> mukBTIResult.modifyScore(mbti.getEI());
                case NS -> mukBTIResult.modifyScore(mbti.getNS());
                case TF -> mukBTIResult.modifyScore(mbti.getTF());
                case JP -> mukBTIResult.modifyScore(mbti.getJP());
                case MINT -> mukBTIResult.modifyScore(mbti.getMint());
                case PINE -> mukBTIResult.modifyScore(mbti.getPine());
                case DIE -> mukBTIResult.modifyScore(mbti.getDie());
            }
            results.add(mukBTIResult);
        }

        mukBTIResultRepository.saveAll(results);
    }

    @Override
    public MukBTIResponse getMukBTI(UserEntity user) {
        List<MukBTIResultEntity> mukBTIResultEntities = mukBTIResultRepository.findAllMukBTIResultByMukboId(
                                user
                                .getMukboEntity()
                                .getMukboId()
                                );

        if(mukBTIResultEntities.size() == 0){
            throw new MukBTIException(MukBTIErrorCode.NOT_FOUND_ERROR);
        }

        return MukBTIResponse.builder()
                .mbti(MBTI.create(mukBTIResultEntities))
                .build();
    }

    private MukBTIQuestionEntity matchQuestion(List<MukBTIQuestionEntity> questions, Integer id){
        for(MukBTIQuestionEntity question : questions){
            if(question.getQuestionId().equals(id)){
                return question;
            }
        }
        throw new MukBTIException(MukBTIErrorCode.NOT_FOUND_QUESTION);
    }

    private MukBTIAnswerEntity matchAnswer(List<MukBTIAnswerEntity> answers, Integer id){
        for(MukBTIAnswerEntity answer : answers){
            if(answer.getAnswerId().equals(id)){
                return answer;
            }
        }
        throw new MukBTIException(MukBTIErrorCode.NOT_FOUND_ANSWER);
    }

}
