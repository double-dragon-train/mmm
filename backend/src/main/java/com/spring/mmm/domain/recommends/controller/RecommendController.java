package com.spring.mmm.domain.recommends.controller;

import com.spring.mmm.domain.recommends.controller.request.LunchRecommendRequest;
import com.spring.mmm.domain.recommends.controller.response.LunchRecommendResponse;
import com.spring.mmm.domain.recommends.controller.response.RecommandRandomFood;
import com.spring.mmm.domain.recommends.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping
    public ResponseEntity<RecommandRandomFood> recommendRandomFood(){
        return ResponseEntity.ok(RecommandRandomFood.builder()
                .foods(recommendService.recommendRandomFood())
                .build());
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<LunchRecommendResponse> recommendLunch(
            @RequestBody LunchRecommendRequest lunchRecommendRequest
    ){
        return ResponseEntity.ok(LunchRecommendResponse.builder()
                .foods(recommendService.lunchRecommendFood(lunchRecommendRequest))
                .build()
        );
    }
}
