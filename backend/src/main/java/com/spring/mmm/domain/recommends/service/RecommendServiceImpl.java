package com.spring.mmm.domain.recommends.service;

import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.recommends.service.port.FoodRecommendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendServiceImpl {

    private final FoodRecommendRepository foodRecommendRepository;
    private final MukgroupRepository mukgroupRepository;

}
