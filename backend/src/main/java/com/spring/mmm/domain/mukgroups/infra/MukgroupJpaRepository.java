package com.spring.mmm.domain.mukgroups.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MukgroupJpaRepository extends JpaRepository<MukgroupEntity, Long> {
    @Query("select mg from MukgroupEntity mg where mg.mukgroupId=:mukgroupId")
    MukgroupEntity findByMukgroupId(Long mukgroupId);
}
