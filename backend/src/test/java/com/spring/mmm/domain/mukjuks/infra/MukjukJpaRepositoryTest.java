package com.spring.mmm.domain.mukjuks.infra;

import com.spring.mmm.domain.mukjuks.controller.response.Badge;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MukjukJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MukjukJpaRepository mukjukJpaRepository;


    @BeforeEach
    void beforeEach(){
        for (int i = 1; i <= 10; i++) {
            em.persist(MukjukEntity.builder()
                    .name("먹적"+i)
                    .context("비밀~")
                    .build());
        }
        em.flush();
    }

    @Test
    void 먹적조회_테스트() throws Exception {
        // given
        long groupId = 1L;
        // when
//        List<Badge> sut = mukjukJpaRepository.findAllMukjuks(groupId);
        List<Badge> sut = em.createQuery("select new com.spring.mmm.domain.mukjuks.controller.response.Badge(mj.mukjukId, mj.name, mj.context, CASE WHEN mgmj is null THEN false" +
                        " ELSE true" +
                        " END , mj.imageSrc ) " +
                        "from MukgroupMukjukEntity mgmj right join mgmj.mukjukEntity mj on mgmj.mukgroupEntity.id =:groupId" , Badge.class)
                .setParameter("groupId", groupId)
                .getResultList();
        // then
        Assertions.assertThat(sut.size()).isSameAs(10);
        System.out.println(sut);

    }
}