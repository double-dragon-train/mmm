package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.common.service.S3Service;
import com.spring.mmm.domain.mbtis.controller.response.MBTIResult;
import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukboErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukboException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MukgroupServiceImpl implements MukgroupService{
    private final MukgroupRepository mukgroupRepository;
    private final MukboRepository mukboRepository;
    private final S3Service s3Service;
    private final MukBTIResultRepository mukBTIResultRepository;
    @Override
    public void saveSoloMukGroup(String name, UserEntity user) {
        MukgroupEntity mukgroupEntity = mukgroupRepository.save(MukgroupEntity.create(name, Boolean.TRUE));
        mukboRepository.save(mukboRepository.findByUserId(user.getId())
                .modifyGroup(mukgroupEntity.getMukgroupId()));
    }

    @Override
    public void saveMukGroup(String name, UserEntity user) {
        MukboEntity mukboEntity = mukboRepository.findByUserId(user.getId());
        MukgroupEntity originMukgroup = mukgroupRepository.findByMukgroupId(mukboEntity.getMukboId());
        if(originMukgroup.getIsSolo()){
            MukgroupEntity mukgroupEntity = mukgroupRepository.save(MukgroupEntity.create(name, Boolean.FALSE));
            mukboRepository.save(mukboEntity.modifyGroup(mukgroupEntity.getMukgroupId()));
            mukgroupRepository.delete(originMukgroup);
        } else {
            throw new MukGroupException(MukGroupErrorCode.DUPLICATE_ERROR);
        }
    }

    @Override
    public MukgroupEntity findMyMukgroup(UserEntity user) {
        MukboEntity mukboEntity = mukboRepository.findByUserId(user.getId());
        return mukgroupRepository.findByMukgroupId(mukboEntity.getMukgroupEntity().getMukgroupId());
    }

    @Override
    public MukgroupEntity findMukgroupById(Long groupId) {
        return mukgroupRepository.findByMukgroupId(groupId);
    }

    @Override
    public void modifyGroupName(Long groupId, String name) {
        mukgroupRepository.save(mukgroupRepository.findByMukgroupId(groupId).modifyMukgroupName(name));
    }

    @Override
    public void modifyGroupImage(Long groupId, MultipartFile multipartFile) {
        String imageSrc = s3Service.uploadFile(multipartFile);
        mukgroupRepository.save(mukgroupRepository.findByMukgroupId(groupId).modifyMukgroupImage(imageSrc));
    }

    @Override
    public void kickMukbo(Long mukboId) {
        MukboEntity mukboEntity = mukboRepository.findByMukboId(mukboId);
        if(mukboEntity.getType() == MukboType.HUMAN) {
            UserEntity user = mukboEntity.getUserEntity();
            saveSoloMukGroup(user.getNickname(), user);
        }
        else {
            mukboRepository.delete(mukboEntity);
        }
    }

    @Override
    public void exitMukgroup(UserDetailsImpl user, Long groupId) {
        if(mukgroupRepository.findByMukgroupId(groupId).getIsSolo()){
            throw new MukGroupException(MukGroupErrorCode.SOLO_CANT_EXIT);
        }

        Integer mukboCount = mukgroupRepository.countAllMukboByMukgroupId(groupId);

        if(mukboCount == 1){
            mukgroupRepository.delete(mukgroupRepository.findByMukgroupId(groupId));
        }

        saveSoloMukGroup(user.getUsername(), user.getUser());
    }

    @Override
    public MBTI calcGroupMukBTI(Long groupId, MukgroupMBTICalcRequest mbtiCalcRequest) {
        MBTI mbti = MBTI.builder().build();
        for(MukBTIType mukBTIType : MukBTIType.values()){
            int calcResult = calcMBTI(mukBTIResultRepository.findAllMukBTIResultByMukboIdAndMukBTIType(mbtiCalcRequest.getMukbos(), mukBTIType));
            mbti.modifyScore(calcResult, mukBTIType);
        }
        return mbti;
    }

    private Integer calcMBTI(List<MukBTIResultEntity> mukBTIResults){
        int sum = 0;
        for(MukBTIResultEntity mukBTIResult : mukBTIResults){
            sum += mukBTIResult.getScore();
        }
        return sum / mukBTIResults.size();
    }
}
