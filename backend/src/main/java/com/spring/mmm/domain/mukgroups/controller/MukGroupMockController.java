package com.spring.mmm.domain.mukgroups.controller;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukbotModifyRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTIResponse;
import com.spring.mmm.domain.mukgroups.controller.response.*;
import com.spring.mmm.domain.mukjuks.domain.Badge;
import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("groups")
public class MukGroupMockController {

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
    public ResponseEntity<MukgroupMukjukResponse> getGroupMukjuks(@PathVariable Long groupId){
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

        MukgroupMukjukResponse groupMukjukResponse = MukgroupMukjukResponse.builder()
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
