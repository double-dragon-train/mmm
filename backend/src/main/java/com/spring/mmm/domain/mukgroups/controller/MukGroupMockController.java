//package com.spring.mmm.domain.mukgroups.controller;
//
//import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
//import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTIResponse;
//import com.spring.mmm.domain.mukgroups.controller.response.*;
//import com.spring.mmm.domain.mukjuks.domain.Badge;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("groups")
//public class MukGroupMockController {
//
//    @GetMapping("{groupId}/badges")
//    public ResponseEntity<MukgroupMukjukResponse> getGroupMukjuks(@PathVariable Long groupId){
//        Badge badge1 = Badge.builder()
//                .id(3L)
//                .name("한국인")
//                .condition("한식100번먹기")
//                .isCleared(Boolean.FALSE)
//                .imageSrc("asdfa")
//                .build();
//
//        Badge badge2 = Badge.builder()
//                .id(666L)
//                .name("민초단")
//                .condition("비밀")
//                .isCleared(Boolean.TRUE)
//                .imageSrc("asdasdasd")
//                .build();
//
//        List<Badge> badges = new ArrayList<>();
//        badges.add(badge1);
//        badges.add(badge2);
//
//        MukgroupMukjukResponse groupMukjukResponse = MukgroupMukjukResponse.builder()
//                .titleMukjukId(20L)
//                .badges(badges)
//                .build();
//
//        return ResponseEntity.ok(groupMukjukResponse);
//    }
//
//    @PutMapping("{groupId}/badges")
//    public ResponseEntity<Void> modifyTitleBadge(
//            @PathVariable Long groupId,
//            @RequestBody Long badgeId
//    ){
//        return ResponseEntity.ok().build();
//    }
//
//}
