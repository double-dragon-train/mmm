package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MukBTIJpaRepository extends JpaRepository<MukBTIEntity, Integer> {
}
