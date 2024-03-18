package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MukBTIQuestionJpaRepository extends JpaRepository<MukBTIQuestionEntity, Integer> {
}
