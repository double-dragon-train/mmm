package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.users.domain.User;

public interface MukgroupService {
    void saveSoloMukGroup(String name, User user);
    void saveMukGroup(String name, User user);
}
