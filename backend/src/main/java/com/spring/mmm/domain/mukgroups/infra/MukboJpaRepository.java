package com.spring.mmm.domain.mukgroups.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MukboJpaRepository extends JpaRepository<MukboEntity, Long> {
    @Query("select m from MukboEntity m where m.userEntity.id=:userId")
    MukboEntity findByUserId(Long userId);
}
