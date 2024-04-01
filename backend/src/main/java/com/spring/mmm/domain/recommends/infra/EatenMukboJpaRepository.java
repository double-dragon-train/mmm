package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.EatenMukboEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EatenMukboJpaRepository extends JpaRepository<EatenMukboEntity, Long> {

}
