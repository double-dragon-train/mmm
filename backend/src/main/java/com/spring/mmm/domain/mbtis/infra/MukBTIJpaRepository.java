package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MukBTIJpaRepository extends JpaRepository<MukBTIEntity, Integer> {

    @Query("select m from MukBTIEntity m where m.mukboEntity.mukboId=:mukboId")
    List<MukBTIEntity> findAllMukBTIByMukboId(@Param("mukboId") Long mukboId);
}
