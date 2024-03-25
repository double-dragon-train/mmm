package com.spring.mmm.domain.mukgroups.service;


import com.spring.mmm.common.event.Events;
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
import com.spring.mmm.domain.mukgroups.event.MukboInvitedEvent;
import com.spring.mmm.domain.mukgroups.event.MukboNicknameChangedEvent;
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
        MukboEntity mukboEntity = mukboRepository.findByUserId(
                userRepository.findByEmail(mukboInviteRequest.getEmail())
                .orElseThrow(() -> new UserException(UserErrorCode.EMAIL_NOT_FOUND)).getId());
        // FIXME 로직 수정 필요. user가 이 먹그룹에 속한지도 체크해야 함. MukboEntity가 일반 그룹에 속해있는지 체크 해야 함, 그리고 먹봇이랑 링크 안할 수 있기 때문에, 아래 delete도 제거해야 함.
        mukboRepository.delete(mukboRepository.findByMukboId(mukboInviteRequest.getMukbotId()));

        mukboEntity.modifyName(mukboInviteRequest.getNickname());
        mukboEntity.modifyGroup(groupId);
        mukboRepository.save(mukboEntity);


        MukboEntity userMukbo = mukboRepository.findByUserId(user.getUser().getId());

        Events.raise(new MukboInvitedEvent(userMukbo.getName(),mukboInviteRequest.getNickname(), groupId));
    }


    @Override
    public void modifyMukbot(UserDetailsImpl user, Long mukboId, MBTI mbti, String name) {
        MukboEntity mukbotEntity = mukboRepository.findByMukboId(mukboId);
        mukbotEntity.modifyName(name);

        List<MukBTIResultEntity> mukBTIResults = mukBTIResultRepository.findAllMukBTIResultByMukboId(mukboId);

        // 먹봇이면 유저 넣을 필요 X
        if(mukBTIResults.isEmpty()){
            createMukBTIResults(mukBTIResults, mukbotEntity, mbti);
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
        Events.raise(new MukboNicknameChangedEvent(name, mukboEntity.getMukgroupEntity().getMukgroupId()));
    }

    @Override
    public void deleteMukbo(Long mukboId) {
        mukboRepository.delete(mukboRepository.findByMukboId(mukboId));
    }

    private void createMukBTIResults(List<MukBTIResultEntity> results, MukboEntity mukbo, MBTI mbti){
        // 원래 mbti가 없는경우
        List<MukBTIEntity> mukBTIs = mukBTIRepository.findAllMukBTI();
        for(MukBTIEntity mukBTI : mukBTIs){
//            Field field = mbti.getClass().getDeclaredField(mukBTI.getType().getTemp());
//            field.setAccessible(true);
//            Integer value = field.get(mbti);
//            results.add(MukBTIResultEntity.createByType(value, mukBTI, mukbo, user));
            switch (mukBTI.getType()){
                case MukBTIType.EI -> results.add(MukBTIResultEntity.createByType(mbti.getEI(), mukBTI, mukbo));
                case MukBTIType.NS -> results.add(MukBTIResultEntity.createByType(mbti.getNS(), mukBTI, mukbo));
                case MukBTIType.TF -> results.add(MukBTIResultEntity.createByType(mbti.getTF(), mukBTI, mukbo));
                case MukBTIType.JP -> results.add(MukBTIResultEntity.createByType(mbti.getJP(), mukBTI, mukbo));
                case MukBTIType.PINE -> results.add(MukBTIResultEntity.createByType(mbti.getPine(), mukBTI, mukbo));
                case MukBTIType.MINT -> results.add(MukBTIResultEntity.createByType(mbti.getMint(), mukBTI, mukbo));
                case MukBTIType.DIE -> results.add(MukBTIResultEntity.createByType(mbti.getDie(), mukBTI, mukbo));
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
