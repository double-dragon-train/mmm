package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.users.domain.User;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MukgroupService {
    void saveSoloMukGroup(String name, UserEntity user);
    void saveMukGroup(String name, UserEntity user);

    MukgroupEntity findMyMukgroup(UserEntity user);

    void modifyGroupName(Long groupId, String name);

    void modifyGroupImage(Long groupId, MultipartFile multipartFile);

    void exitMukgroup(UserEntity user);
}
