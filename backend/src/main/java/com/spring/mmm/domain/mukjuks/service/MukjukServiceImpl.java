package com.spring.mmm.domain.mukjuks.service;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.mukjuks.controller.response.MukjukResponse;
import com.spring.mmm.domain.mukjuks.service.port.MukjukRepository;
import com.spring.mmm.domain.muklogs.exception.MukgroupNotFoundException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MukjukServiceImpl implements MukjukService {
    private final MukjukRepository mukjukRepository;
    private final MukgroupRepository mukgroupRepository;
    private final MukboRepository mukboRepository;
    @Override
    public MukjukResponse findAllMukjuks(Long groupId, UserDetailsImpl users) {
        // groupId 검증
        MukgroupEntity mukgroup = validateFindAllMukjuks(groupId, users);
        return MukjukResponse.create(mukgroup,mukjukRepository.findAllBadges(groupId));
    }

    private MukgroupEntity validateFindAllMukjuks(Long groupId, UserDetailsImpl users) {
        MukgroupEntity mukgroup = mukgroupRepository.findByMukgroupId(groupId)
                .orElseThrow(MukgroupNotFoundException::new);
        // user조회하고, mukbo가 group에 속한지 검증
        if (mukgroup.getIsSolo()) {
            throw new MukGroupException(MukGroupErrorCode.SOLO_CANT_ACCESS_MUKJUK);
        }
        MukboEntity mukbo = mukboRepository.findByUserId(users.getUser().getId());
        if (!mukbo.getMukgroupEntity().equals(mukgroup)) {
            throw new MukGroupException(MukGroupErrorCode.FORBIDDEN);
        }
        return mukgroup;
    }
}
