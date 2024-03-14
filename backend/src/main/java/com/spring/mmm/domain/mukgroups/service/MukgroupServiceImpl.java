package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mukgroups.domain.Mukbo;
import com.spring.mmm.domain.mukgroups.domain.Mukgroup;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.infra.MukboEntity;
import com.spring.mmm.domain.mukgroups.infra.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MukgroupServiceImpl implements MukgroupService{
    private final MukgroupRepository mukgroupRepository;
    private final MukboRepository mukboRepository;
    @Override
    public void saveSoloMukGroup(String name, User user) {
        Mukgroup mukgroup = mukgroupRepository.save(MukgroupEntity.fromSolo(name));
        Mukbo mukbo = mukboRepository.findByUserId(user.getId()).to(mukgroup.getMukgroupId());
        mukboRepository.save(MukboEntity.from(mukbo));
    }

    @Override
    public void saveMukGroup(String name, User user) {
        Mukbo mukbo = mukboRepository.findByUserId(user.getId()).to();
        Mukgroup originMukgroup = mukgroupRepository.findByMukgroupId(mukbo.getMukgroupId());
        if(originMukgroup.getIsSolo()){
            mukgroupRepository.delete(MukgroupEntity.from(mukgroupRepository.findByMukgroupId(mukbo.getMukgroupId())));
            Mukgroup mukgroup = Mukgroup.builder()
                    .name(name)
                    .isSolo(Boolean.FALSE)
                    .build();
            mukgroupRepository.save(MukgroupEntity.from(mukgroup));
        } else {
            throw new MukGroupException(MukGroupErrorCode.ALREADY_EXISTS);
        }
    }
}
