package com.spring.mmm.domain.mukgroups.controller;

import com.spring.mmm.domain.mukgroups.controller.request.*;
import com.spring.mmm.domain.mukgroups.controller.response.MukbosResponse;
import com.spring.mmm.domain.mukgroups.controller.response.MukgroupResponse;
import com.spring.mmm.domain.mukgroups.service.MukboService;
import com.spring.mmm.domain.mukgroups.service.MukgroupService;
import com.spring.mmm.domain.muklogs.controller.response.MuklogsResponse;
import com.spring.mmm.domain.muklogs.service.MuklogService;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("groups")
public class MukGroupController {

    private final MukgroupService mukgroupService;
    private final MukboService mukboService;
    private final MuklogService muklogService;

    @GetMapping
    public ResponseEntity<MukgroupResponse> findMukgroup(@AuthenticationPrincipal UserDetailsImpl user){
        return ResponseEntity.ok(mukgroupService.findMyMukgroup(user.getUser()).createMukgroupResponse());
    }

    @PostMapping
    public ResponseEntity<Void> createMukGroup(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestPart(value = "data", required = true) MukgroupCreateRequest mukgroupCreateRequest,
            @RequestPart(value = "image", required = false) MultipartFile image
    ){
        mukgroupService.saveMukGroup(mukgroupCreateRequest.getName(), user.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{groupId}/name")
    public ResponseEntity<Void> modifyGroupName(
            @PathVariable Long groupId,
            @RequestBody MukgroupModifyRequest mukgroupModifyRequest,
            @AuthenticationPrincipal UserDetailsImpl users){
        mukgroupService.modifyGroupName(groupId, mukgroupModifyRequest.getName(), users);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{groupId}/image")
    public ResponseEntity<Void> modifyGroupImage(
            @PathVariable Long groupId,
            @RequestPart(value = "image") MultipartFile image,
            @AuthenticationPrincipal UserDetailsImpl users){
        mukgroupService.modifyGroupImage(groupId, image, users);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{groupId}/users")
    public ResponseEntity<MukbosResponse> findAllMukbos(@PathVariable Long groupId){
        return ResponseEntity.ok(MukbosResponse.builder()
                .users(mukboService.findAllMukboResponsesByGroupId(groupId))
                .build());
    }

    @GetMapping("{groupId}/mukbots")
    public ResponseEntity<MukbosResponse> findAllMukbots(@PathVariable Long groupId){
        return ResponseEntity.ok(MukbosResponse.builder()
                .users(mukboService.findAllMukbotResponsesByGroupId(groupId))
                .build());
    }

    @GetMapping("/{groupId}/log")
    public ResponseEntity<MuklogsResponse> findAllLogsByPaging(@PathVariable Long groupId, Pageable pageable,
                                                               @AuthenticationPrincipal UserEntity userEntity){
        return ResponseEntity.ok(muklogService.findAllMuklogByGroupId(groupId, pageable, userEntity));
    }

    @PostMapping("{groupId}/users")
    public ResponseEntity<Void> inviteUser(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable Long groupId,
            @RequestBody MukboInviteRequest mukboInviteRequest
    ){
        mukboService.inviteMukbo(user, groupId, mukboInviteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{groupId}/users/{userId}/nickname")
    public ResponseEntity<Void> modifyMukboName(
            @PathVariable Long userId,
            @RequestBody MukboModifyRequest mukboModifyRequest){
        mukboService.modifyMokbo(userId, mukboModifyRequest.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("{groupId}/mukbots/{mukbotsId}")
    public ResponseEntity<Void> modifyMukbot(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable Long mukbotsId,
            @RequestBody MukbotModifyRequest mukbotModifyRequest
    ){
        mukboService.modifyMukbot(user, mukbotsId, mukbotModifyRequest.getMbti(), mukbotModifyRequest.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{groupId}/mukbos/{mokboId}")
    public ResponseEntity<Void> deleteMukbo(
            @PathVariable Long mukboId,
            @AuthenticationPrincipal UserDetailsImpl users){
        mukgroupService.kickMukbo(mukboId, users);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{groupId}/exit")
    public ResponseEntity<Void> exitMukgroup(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable Long groupId){
        mukgroupService.exitMukgroup(user, groupId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{groupId}/mbti")
    public ResponseEntity<MukgroupMBTIResponse> getGroupMBTI(
            @PathVariable Long groupId,
            @RequestBody MukgroupMBTICalcRequest mbtiCalcRequest
    ){
        return ResponseEntity.ok(MukgroupMBTIResponse
                .builder()
                .mbti(mukgroupService.calcGroupMukBTI(groupId, mbtiCalcRequest))
                .build());
    }
}
