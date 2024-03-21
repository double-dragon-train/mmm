package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.common.service.S3Service;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.users.infra.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MukgroupServiceImpl implements MukgroupService{
    private final MukgroupRepository mukgroupRepository;
    private final MukboRepository mukboRepository;
    private final S3Service s3Service;
    @Override
    public void saveSoloMukGroup(String name, UserEntity user) {
        MukgroupEntity mukgroupEntity = mukgroupRepository.save(MukgroupEntity.create(name, Boolean.TRUE));
        mukboRepository.save(mukboRepository.findByUserId(user.getId()).modifyGroup(mukgroupEntity.getMukgroupId()));
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
        return mukgroupRepository.findByMukgroupId(mukboRepository.findByUserId(user.getId()).getMukgroupEntity().getMukgroupId());
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
    public void exitMukgroup(UserEntity user) {
        if(mukgroupRepository.findByMukgroupId(mukboRepository.findByUserId(user.getId()).getMukboId()).getIsSolo()){
            throw new MukGroupException(MukGroupErrorCode.SOLO_CANT_EXIT);
        }
        saveSoloMukGroup(user.getNickname(), user);
    }
}
