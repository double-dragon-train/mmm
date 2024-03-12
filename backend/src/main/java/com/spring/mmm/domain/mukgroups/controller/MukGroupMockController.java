package com.spring.mmm.domain.mukgroups.controller;

import com.spring.mmm.domain.mbtis.controller.response.MBTI;
import com.spring.mmm.domain.mukgroups.controller.response.GroupResponse;
import com.spring.mmm.domain.mukgroups.controller.response.MukLog;
import com.spring.mmm.domain.mukgroups.controller.response.MukLogResponse;
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


}
