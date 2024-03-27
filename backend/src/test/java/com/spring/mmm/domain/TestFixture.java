package com.spring.mmm.domain;

import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukjuks.controller.response.Badge;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import com.spring.mmm.domain.users.infra.UserEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class TestFixture {
    public static UserEntity user;
    public static UserEntity userWithMukbo;
    public static UserEntity userWithMukboSoloGroup;
    public static MukgroupEntity soloMukgroupEntity;
    public static MukgroupEntity mukgroupEntity;
    public static MukboEntity mukboEntitySologroup;
    public static MukboEntity mukboEntityGroup;
    public static MukboEntity mukbotEntity;
    public static MukBTIResultEntity mukBTIResult;
    public static List<Badge> badges;


    static {
        badges = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            badges.add(Badge.builder()
                    .id((long) i)
                    .name("먹적"+i)
                    .isCleared(i<5)
                    .build());
        }

        MukjukEntity mukjuk = MukjukEntity.builder()
                .name("먹적먹적")
                .context("이 먹적은 회득 조건을 공개하지 않습니다.")
                .imageSrc("")
                .mukjukId(100L)
                .build();

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
                .mukjukEntity(mukjuk)
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

        userWithMukbo = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .mukboEntity(mukboEntityGroup)
                .build();
        // 먹보와함께유저

        userWithMukboSoloGroup = UserEntity.builder()
                .id(1L)
                .email("ssafy@ssafy.com")
                .nickname("ssafy")
                .mukboEntity(mukboEntitySologroup)
                .build();
        // 솔로먹그룹유저

        mukBTIResult = MukBTIResultEntity.builder()
                .score(30)
                .build();
        // 먹비티아이장전


        // 먹적


    }
}
