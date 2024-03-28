package com.spring.mmm.domain.mukjuks.service;

import com.spring.mmm.domain.TestFixture;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.mukjuks.controller.response.MukjukResponse;
import com.spring.mmm.domain.mukjuks.service.port.MukjukRepository;
import com.spring.mmm.domain.muklogs.exception.MukgroupNotFoundException;
import com.spring.mmm.domain.muklogs.exception.MuklogErrorCode;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MukjukServiceImplTest {

    @Mock
    MukjukRepository mukjukRepository;
    @Mock
    MukgroupRepository mukgroupRepository;
    @Mock
    MukboRepository mukboRepository;

    @InjectMocks
    MukjukServiceImpl mukjukService;

    @Test
    void 먹적_조회_테스트_성공() throws Exception {
        // given
        Long groupId = 1L;
        UserDetailsImpl users = new UserDetailsImpl(
                UserEntity.builder()
                        .id(1L)
                        .build()
                , "tester@naver.com");


        given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(TestFixture.mukgroupEntity));
        given(mukboRepository.findByUserId(any()))
                .willReturn(TestFixture.mukboEntityGroup);
        given(mukjukRepository.findAllBadges(any()))
                .willReturn(TestFixture.badges);

        // when
        MukjukResponse sut = mukjukService.findAllMukjuks(groupId, users);
        // then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(sut.getTitleMukjukId()).isEqualTo(100L);
        sa.assertThat(sut.getBadges().size()).isSameAs(10);
        sa.assertAll();
    }

    @Test
    void 먹적_조회_테스트_실패_그룹_아이디가_존재하지_않는_경우() throws Exception {
// given
        Long groupId = 1L;
        UserDetailsImpl users = new UserDetailsImpl(
                UserEntity.builder()
                        .id(1L)
                        .build()
                , "tester@naver.com");

        given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.empty());
        // when // then
        assertThatThrownBy(() -> mukjukService.findAllMukjuks(groupId, users))
                .isInstanceOf(MukGroupException.class)
                .hasFieldOrPropertyWithValue("errorCode", MukGroupErrorCode.MUKGROUP_NOT_FOUND);
    }

    @Test
    void 먹적_조회_테스트_실패_내가_그룹에_속하지_않는_경우() throws Exception {
        Long groupId = 1L;
        UserDetailsImpl users = new UserDetailsImpl(
                UserEntity.builder()
                        .id(1L)
                        .build()
                , "tester@naver.com");

        given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(TestFixture.mukgroupEntity));
        given(mukboRepository.findByUserId(any()))
                .willReturn(TestFixture.mukboEntitySologroup);
        // when // then
        assertThatThrownBy(() -> mukjukService.findAllMukjuks(groupId, users))
                .isInstanceOf(MukGroupException.class)
                .hasFieldOrPropertyWithValue("errorCode", MukGroupErrorCode.FORBIDDEN);
    }

    @Test
    void 먹적_조회_테스트_실패_그룹이_솔로_그룹인_경우() throws Exception {
        Long groupId = 1L;
        UserDetailsImpl users = new UserDetailsImpl(
                UserEntity.builder()
                        .id(1L)
                        .build()
                , "tester@naver.com");

        given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(TestFixture.soloMukgroupEntity));
        // when // then
        assertThatThrownBy(() -> mukjukService.findAllMukjuks(groupId, users))
                .isInstanceOf(MukGroupException.class)
                .hasFieldOrPropertyWithValue("errorCode", MukGroupErrorCode.SOLO_CANT_ACCESS_MUKJUK);
    }


}