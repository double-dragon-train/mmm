package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MukgroupService {
    void saveSoloMukGroup(String name, UserEntity user);
    void saveMukGroup(String name, UserEntity user, MultipartFile image);

    MukgroupEntity findMyMukgroup(UserEntity user);

    MukgroupEntity findMukgroupById(Long groupId);

    void modifyGroupName(Long groupId, String name, UserEntity users);

    void modifyGroupImage(Long groupId, MultipartFile multipartFile, UserDetailsImpl users);

    void kickMukbo(UserEntity user, Long groupId, Long mukboId);

    void exitMukgroup(UserEntity user, Long groupId);

    MBTI calcGroupMukBTI(Long groupId, MukgroupMBTICalcRequest mbtiCalcRequest);
}
