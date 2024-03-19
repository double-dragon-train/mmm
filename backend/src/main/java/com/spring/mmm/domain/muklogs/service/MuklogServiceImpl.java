package com.spring.mmm.domain.muklogs.service;

import com.spring.mmm.domain.muklogs.controller.response.MuklogsResponse;
import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import com.spring.mmm.domain.muklogs.service.port.MuklogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MuklogServiceImpl implements MuklogService{
    private final MuklogRepository muklogRepository;


    @Override
    public MuklogsResponse findAllMuklogByGroupId(Long groupId, Pageable pageable) {
        Page<MuklogEntity> result = muklogRepository.findAllMuklogByGroupId(groupId, pageable);
        return MuklogsResponse.builder()
                .contents(result.getContent().stream().map(item -> item.create()).toList())
                .hasNext(result.hasNext())
                .build();
    }
}
