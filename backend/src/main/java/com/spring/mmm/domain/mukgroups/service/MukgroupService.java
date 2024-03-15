package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.users.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface MukgroupService {
    void saveSoloMukGroup(String name, User user);
    void saveMukGroup(String name, User user);

    MukgroupEntity findMyMukgroup(User user);

    void modifyGroupName(Long groupId, String name);

    void modifyGroupImage(Long groupId, MultipartFile multipartFile);

    void exitMukgroup(User user);
}
