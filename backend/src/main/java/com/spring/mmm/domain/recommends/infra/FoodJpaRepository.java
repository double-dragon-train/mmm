package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodJpaRepository extends JpaRepository<FoodEntity, Long> {

    FoodEntity findByName(String name);

    List<FoodEntity> findAll();

}
