package com.spring.mmm.domain.mukgroups.controller;

import com.spring.mmm.domain.mbtis.controller.response.MBTI;
import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukbotModifyRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTIResponse;
import com.spring.mmm.domain.mukgroups.controller.response.*;
import com.spring.mmm.domain.mukgroups.domain.Badge;
import com.spring.mmm.domain.mukgroups.domain.MukLog;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("groups")
public class MukGroupMockController {

    @GetMapping
    public ResponseEntity<GroupResponse> getGroup(){
        // 1. 그룹 검색
        MBTI mbti = MBTI.builder()
                .EI(60)
                .NS(10)
                .TF(5)
                .JP(80)
                .Mint(100)
                .Pine(100)
                .Die(0)
                .build();

        GroupResponse groupResponse = GroupResponse.builder()
                .groupId(1L)
                .name("장현수와 아이들")
                .solo(Boolean.FALSE)
                .mbti(mbti)
                .titleMukjukId(1L)
                .build();

        return ResponseEntity.ok(groupResponse);
    }

    @PostMapping
    public ResponseEntity<Void> createMukGroup(
            @RequestPart(value = "data", required = true) String name,
            @RequestPart(value = "image", required = false) MultipartFile image
            ){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{groupId}/name")
    public ResponseEntity<Void> modifyGroupName(@PathVariable Long groupId, @RequestBody String name){
        return ResponseEntity.ok().build();
    }

    @GetMapping("{groudId}/log")
    public ResponseEntity<MukLogResponse> findMukLog(
            @PathVariable Long groupId,
            @PathParam("page") Long page,
            @PathParam("size") Long size
    ){
        MukLog mukLog1 = MukLog.builder()
                .content("로그1")
                .createdAt(123123L)
                .build();

        MukLog mukLog2 = MukLog.builder()
                .content("로그2")
                .createdAt(456456L)
                .build();

        List<MukLog> logs = new ArrayList<>();
        logs.add(mukLog1);
        logs.add(mukLog2);
        MukLogResponse mukLogResponse = MukLogResponse.builder()
                .contents(logs)
                .hasNext(false)
                .build();

        return ResponseEntity.ok(mukLogResponse);
    }

    @GetMapping("{groupId}/users")
    public ResponseEntity<MukbosResponse> findAllMukbos(@PathVariable Long groupId){
        MBTI mbti = MBTI.builder()
                .EI(80)
                .NS(20)
                .TF(40)
                .JP(10)
                .Mint(100)
                .Pine(0)
                .Die(100)
                .build();
        MukboResponse mukboResponse1 = MukboResponse.builder()
                .type(MukboType.HUMAN)
                .name("수현장")
                .mbti(mbti)
                .build();

        MukboResponse mukboResponse2 = MukboResponse.builder()
                .type(MukboType.HUMAN)
                .name("로봇아닌현수")
                .mbti(mbti)
                .build();

        List<MukboResponse> users = new ArrayList<>();
        users.add(mukboResponse1);
        users.add(mukboResponse2);

        MukbosResponse mukbosResponse = MukbosResponse.builder()
                .users(users)
                .build();

        return ResponseEntity.ok(mukbosResponse);
    }

    @GetMapping("{groupId}/mukbots")
    public ResponseEntity<MukbosResponse> findAllMukbots(@PathVariable Long groupId){
        MBTI mbti = MBTI.builder()
                .EI(80)
                .NS(20)
                .TF(40)
                .JP(10)
                .Mint(100)
                .Pine(0)
                .Die(100)
                .build();
        MukboResponse mukboResponse1 = MukboResponse.builder()
                .type(MukboType.MUKBOT)
                .name("수장현")
                .mbti(mbti)
                .build();

        MukboResponse mukboResponse2 = MukboResponse.builder()
                .type(MukboType.MUKBOT)
                .name("로봇현수")
                .mbti(mbti)
                .build();

        List<MukboResponse> users = new ArrayList<>();
        users.add(mukboResponse1);
        users.add(mukboResponse2);

        MukbosResponse mukbosResponse = MukbosResponse.builder()
                .users(users)
                .build();

        return ResponseEntity.ok(mukbosResponse);
    }

    @PostMapping("{groupId}/users")
    public ResponseEntity<Void> inviteUser(
            @PathVariable Long groupId,
            @RequestBody MukboInviteRequest mukboInviteRequest
            ){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{groupId}/users/{userId}/nickname")
    public ResponseEntity<Void> modifyMukboName(
            @PathVariable Long groupId,
            @PathVariable Long userId,
            @RequestBody String name){
        return ResponseEntity.ok().build();
    }

    @PostMapping("{groupId}/mukbots")
    public ResponseEntity<Void> inviteMokbot(
            @PathVariable Long groupId,
            @RequestBody MBTI mbti
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{groupId}/mukbots/{mukbotsId}")
    public ResponseEntity<Void> modifyMukbot(
            @PathVariable Long groupId,
            @PathVariable Long mukbotsId,
            @RequestBody MukbotModifyRequest mukbotModifyRequest
            ){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{groupId}/mukbos/{mokboId}")
    public ResponseEntity<Void> deleteMukbo(@PathVariable Long groupId, @PathVariable Long mokboId){
        return ResponseEntity.ok().build();
    }

    @PostMapping("{groupId}/mbti")
    public ResponseEntity<MukgroupMBTIResponse> getGroupMBTI(
            @PathVariable Long groupId,
            @RequestBody MukgroupMBTICalcRequest mbtiCalcRequest
            ){
        return ResponseEntity.ok().build();
    }

    @GetMapping("{groupId}/badges")
    public ResponseEntity<GroupMukjukResponse> getGroupMukjuks(@PathVariable Long groupId){
        Badge badge1 = Badge.builder()
                .id(3L)
                .name("한국인")
                .condition("한식100번먹기")
                .isCleared(Boolean.FALSE)
                .imageSrc("asdfa")
                .build();

        Badge badge2 = Badge.builder()
                .id(666L)
                .name("민초단")
                .condition("비밀")
                .isCleared(Boolean.TRUE)
                .imageSrc("asdasdasd")
                .build();

        List<Badge> badges = new ArrayList<>();
        badges.add(badge1);
        badges.add(badge2);

        GroupMukjukResponse groupMukjukResponse = GroupMukjukResponse.builder()
                .titleMukjukId(20L)
                .badges(badges)
                .build();

        return ResponseEntity.ok(groupMukjukResponse);
    }

    @PutMapping("{groupId}/badges")
    public ResponseEntity<Void> modifyTitleBadge(
            @PathVariable Long groupId,
            @RequestBody Long badgeId
    ){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{groupId}/exit")
    public ResponseEntity<Void> exitMukgroup(@PathVariable Long groupId){
        return ResponseEntity.ok().build();
    }

}
