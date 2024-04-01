package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.EatenMukboEntity;
import com.spring.mmm.domain.recommends.service.port.EatenMukboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EatenMukboRepositoryImpl implements EatenMukboRepository {

    private final EatenMukboJpaRepository eatenMukboJpaRepository;

    @Override
    public void save(EatenMukboEntity eatenMukboEntity) {
        eatenMukboJpaRepository.save(eatenMukboEntity);
    }
}
