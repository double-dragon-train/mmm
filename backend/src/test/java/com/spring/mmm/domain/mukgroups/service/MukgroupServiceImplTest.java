package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.common.exception.S3Exception;
import com.spring.mmm.common.service.S3Service;
import com.spring.mmm.common.service.S3ServiceImpl;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.muklogs.exception.MukgroupNotFoundException;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MukgroupServiceImplTest {

    @Mock
    private MukgroupRepository mukgroupRepository;

    @Mock
    private MukboRepository mukboRepository;

    @Mock
    private S3Service s3Service;

    @InjectMocks
    private MukgroupServiceImpl mukgroupService;

    private static UserEntity user;
    private static MukgroupEntity soloMukgroupEntity;
    private static MukgroupEntity mukgroupEntity;
    private static MukboEntity mukboEntitySologroup;

    private static MukboEntity mukboEntityGroup;
    private static MukboEntity mukbotEntity;

    @BeforeAll
    static void 자료장전(){
        user = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .build();
        // 유저장전
        
        mukgroupEntity = MukgroupEntity.builder()
                .isSolo(Boolean.FALSE)
                .mukgroupId(1L)
                .name("ssafygroup")
                .build();
        // 먹그룹장전
        
        soloMukgroupEntity = MukgroupEntity.builder()
                .mukgroupId(2L)
                .isSolo(Boolean.TRUE)
                .name("ssafysologroup")
                .build();
        // 솔로먹그룹장전

        mukboEntitySologroup = MukboEntity.builder()
                .mukboId(1L)
                .type(MukboType.HUMAN)
                .name("mukssafy")
                .userEntity(user)
                .mukgroupEntity(soloMukgroupEntity)
                .build();
        // 솔로그룹먹보장전

        mukboEntityGroup = MukboEntity.builder()
                .mukboId(1L)
                .type(MukboType.HUMAN)
                .name("mukssafy")
                .userEntity(user)
                .mukgroupEntity(mukgroupEntity)
                .build();
        // 다인그룹먹보장전
        
        mukbotEntity = MukboEntity.builder()
                .mukboId(3L)
                .type(MukboType.MUKBOT)
                .name("mukbot")
                .mukgroupEntity(mukgroupEntity)
                .build();
        // 먹봇장전
    }

    @Test
    void 솔로먹그룹생성_성공(){

        BDDMockito.given(mukgroupRepository.save(any()))
                        .willReturn(soloMukgroupEntity);

        BDDMockito.given(mukboRepository.findByUserId(1L))
                        .willReturn(mukboEntitySologroup);

        assertDoesNotThrow(() -> mukgroupService.saveSoloMukGroup("1234", user));
    }

    @Test
    void 먹그룹생성_성공(){
        BDDMockito.given(mukboRepository.findByUserId(any()))
                .willReturn(mukboEntitySologroup);

        BDDMockito.given(mukgroupRepository.save(any()))
                .willReturn(mukgroupEntity);

        assertDoesNotThrow(() -> mukgroupService.saveMukGroup("1234", user));
    }

    @Test
    void 다인먹그룹중복생성_실패(){
        BDDMockito.given(mukboRepository.findByUserId(any()))
                .willReturn(mukboEntityGroup);

        assertThrows(MukGroupException.class, () -> mukgroupService.saveMukGroup("1234", user));
    }

    @Test
    void 내_먹그룹찾기_성공(){
        BDDMockito.given(mukboRepository.findByUserId(any()))
                .willReturn(mukboEntityGroup);

        assertDoesNotThrow(() -> mukgroupService.findMyMukgroup(user));
    }

    @Test
    void 먹그룹찾기_성공(){
        BDDMockito.given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(mukgroupEntity));

        assertDoesNotThrow(() -> mukgroupService.findMukgroupById(1L));
    }

    @Test
    void 없는먹그룹찾기_실패(){
        BDDMockito.given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.empty());

        assertThrows(MukgroupNotFoundException.class, () -> mukgroupService.findMukgroupById(1L));
    }

    @Test
    void 먹그룹이름수정_성공(){
        BDDMockito.given(mukgroupRepository.findByMukgroupId(any()))
                .willReturn(Optional.of(mukgroupEntity));

        assertDoesNotThrow(() -> mukgroupService.modifyGroupName(1L, "1234"));
    }

    @Test
    void 먹보강퇴_성공(){
        BDDMockito.given(mukboRepository.findByMukboId(any()))
                .willReturn(mukboEntityGroup);

        BDDMockito.given(mukgroupRepository.save(any()))
                        .willReturn(mukgroupEntity);

        BDDMockito.given(mukboRepository.findByUserId(any()))
                        .willReturn(mukboEntityGroup);
        
        assertDoesNotThrow(() -> mukgroupService.kickMukbo(1L));
    }

    @Test
    void 먹봇강퇴_성공(){
        BDDMockito.given(mukboRepository.findByMukboId(any()))
                .willReturn(mukbotEntity);

        assertDoesNotThrow(() -> mukgroupService.kickMukbo(3L));
    }



}