package com.spring.mmm.domain.muklogs.service;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.muklogs.controller.response.MuklogsResponse;
import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import com.spring.mmm.domain.muklogs.exception.MukgroupNotFoundException;
import com.spring.mmm.domain.muklogs.service.port.MuklogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MuklogServiceImpl implements MuklogService{
    private final MuklogRepository muklogRepository;
    private final MukgroupRepository mukgroupRepository;


    @Override
    public MuklogsResponse findAllMuklogByGroupId(Long groupId, Pageable pageable) {
        Page<MuklogEntity> result = muklogRepository.findAllMuklogByGroupId(groupId, pageable);
        return MuklogsResponse.builder()
                .contents(result.getContent().stream().map(item -> item.toReseponse()).toList())
                .hasNext(result.hasNext())
                .build();
    }

    @Override
    public void saveLog(Long mukgroupId, String content) {
        // TODO 옵셔널로 변경해야 함
        MukgroupEntity mukGroup = mukgroupRepository.findByMukgroupId(mukgroupId);
        if(mukGroup == null )
            throw new MukgroupNotFoundException();
        muklogRepository.save(MuklogEntity.create(mukGroup, content));
    }
}
