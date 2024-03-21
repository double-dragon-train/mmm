package com.spring.mmm.domain.mukgroups.service;


import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MukboServiceImpl implements MukboService{
    private final MukboRepository mukboRepository;
    private final UserRepository userRepository;
    private final MukBTIRepository mukBTIRepository;
    private final MukBTIResultRepository mukBTIResultRepository;

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
        mukboEntity.modifyGroup(groupId);
        mukboRepository.save(mukboEntity);
    }


    @Override
    public void modifyMukbot(UserDetailsImpl user, Long mukboId, MBTI mbti, String name) {
        MukboEntity mukbotEntity = mukboRepository.findByMukboId(mukboId);
        mukbotEntity.modifyName(name);

        List<MukBTIResultEntity> mukBTIResults = mukBTIResultRepository.findAllMukBTIResultByMukboId(mukboId);

        if(mukBTIResults.isEmpty()){
            createMukBTIResults(mukBTIResults, user.getUser(), mukbotEntity, mbti);
        } else {
            modifyMukBTIResults(mukBTIResults, mbti);
        }

        mukbotEntity.modifyMukBTIResult(mukBTIResults);
        mukboRepository.save(mukbotEntity);
    }

    @Override
    public void modifyMokbo(Long userId, String name) {
        MukboEntity mukboEntity = mukboRepository.findByUserId(userId);
        mukboEntity.modifyName(name);
        mukboRepository.save(mukboEntity);
    }

    @Override
    public void deleteMukbo(Long mukboId) {
        mukboRepository.delete(mukboRepository.findByMukboId(mukboId));
    }

    private void createMukBTIResults(List<MukBTIResultEntity> results, UserEntity user, MukboEntity mukbo, MBTI mbti){
        // 원래 mbti가 없는경우
        List<MukBTIEntity> mukBTIs = mukBTIRepository.findAllMukBTI();
        for(MukBTIEntity mukBTI : mukBTIs){
//            Field field = mbti.getClass().getDeclaredField(mukBTI.getType().getTemp());
//            field.setAccessible(true);
//            Integer value = field.get(mbti);
//            results.add(MukBTIResultEntity.createByType(value, mukBTI, mukbo, user));
            switch (mukBTI.getType()){
                case MukBTIType.EI -> results.add(MukBTIResultEntity.createByType(mbti.getEI(), mukBTI, mukbo, user));
                case MukBTIType.NS -> results.add(MukBTIResultEntity.createByType(mbti.getNS(), mukBTI, mukbo, user));
                case MukBTIType.TF -> results.add(MukBTIResultEntity.createByType(mbti.getTF(), mukBTI, mukbo, user));
                case MukBTIType.JP -> results.add(MukBTIResultEntity.createByType(mbti.getJP(), mukBTI, mukbo, user));
                case MukBTIType.PINE -> results.add(MukBTIResultEntity.createByType(mbti.getPine(), mukBTI, mukbo, user));
                case MukBTIType.MINT -> results.add(MukBTIResultEntity.createByType(mbti.getMint(), mukBTI, mukbo, user));
                case MukBTIType.DIE -> results.add(MukBTIResultEntity.createByType(mbti.getDie(), mukBTI, mukbo, user));
            }
        }
    }

    private void modifyMukBTIResults(List<MukBTIResultEntity> results, MBTI mbti){
        // 원래 mbti가 있는경우
        for(MukBTIResultEntity mukBTIResult : results){
            switch (mukBTIResult.getMukBTIEntity().getType()){
                case MukBTIType.EI -> mukBTIResult.modifyScore(mbti.getEI());
                case MukBTIType.NS -> mukBTIResult.modifyScore(mbti.getNS());
                case MukBTIType.TF -> mukBTIResult.modifyScore(mbti.getTF());
                case MukBTIType.JP -> mukBTIResult.modifyScore(mbti.getJP());
                case MukBTIType.MINT -> mukBTIResult.modifyScore(mbti.getMint());
                case MukBTIType.PINE -> mukBTIResult.modifyScore(mbti.getPine());
                case MukBTIType.DIE -> mukBTIResult.modifyScore(mbti.getDie());
            }
        }
    }
}
