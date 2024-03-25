package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.common.service.RedisRepository;
import com.spring.mmm.domain.mbtis.controller.request.CalcInfo;
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
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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
        List<MukBTIQuestionEntity> questions = findAllMukBTIQuestion();

        int EI = 0, NS = 0, TF = 0, JP = 0, Mint = 0, Pine = 0, Die = 0;
        for (CalcInfo calcInfo : mukBTICalcRequest.getAnswers()) {
            for (MukBTIQuestionEntity question : questions) {
                if (question.getQuestionId() == calcInfo.getQuizId()) {
                    for (MukBTIAnswerEntity answer : question.getMukBTIAnswerEntities()) {
                        if (answer.getAnswerId() == calcInfo.getAnswerId()) {
                            switch (question.getMukBTIEntity().getType()) {
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
                .EI(EI / 3)
                .NS(NS / 3)
                .TF(TF / 3)
                .JP(JP / 3)
                .Mint(Mint)
                .Pine(Pine)
                .Die(Die)
                .build();

        String key = UUID.randomUUID().toString();
        redisRepository.saveData(key, mbti);
        return MukBTIResult.builder().key(key).mbti(mbti).build();
    }

    @Override
    public void save(UserDetailsImpl user, String key) {
        MBTI mbti = redisRepository.getData(key, MBTI.class)
                .orElseThrow(() -> new MukBTIException(MukBTIErrorCode.NOT_FOUND));

        MukboEntity mukboEntity = user.getUser().getMukboEntity();

        List<MukBTIResultEntity> results = new ArrayList<>();
        List<MukBTIEntity> mukBTIs = mukBTIRepository.findAllMukBTI();

        for(MukBTIEntity mukBTIEntity : mukBTIs){
            MukBTIResultEntity mukBTIResult = MukBTIResultEntity.createWithoutScore(mukBTIEntity, mukboEntity, user.getUser());
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
    public MukBTIResponse getMukBTI(UserDetailsImpl user) {
        return MukBTIResponse.builder()
                .mbti(MBTI
                        .create(mukBTIResultRepository
                                .findAllMukBTIResultByMukboId(user
                                        .getUser()
                                        .getMukboEntity()
                                        .getMukboId())))
                .build();
    }

}
