package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MukgroupService {
    void saveSoloMukGroup(String name, UserEntity user);
    void saveMukGroup(String name, UserEntity user);

    MukgroupEntity findMyMukgroup(UserEntity user);

    MukgroupEntity findMukgroupById(Long groupId);

    void modifyGroupName(Long groupId, String name);

    void modifyGroupImage(Long groupId, MultipartFile multipartFile);

    void kickMukbo(UserEntity user, Long groupId, Long mukboId);

    void exitMukgroup(UserEntity user);

    MBTI calcGroupMukBTI(Long groupId, MukgroupMBTICalcRequest mbtiCalcRequest);
}
