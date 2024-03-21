package com.spring.mmm.domain.recommends.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RecommendController {


    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @GetMapping("/recommend")
    public Map<String, List<Map<String,Object>>> recommendRandomFood(){

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select name, image as 'imageSrc' from food");
        System.out.println(maps);

        Map<String, List<Map<String,Object>>> map = new HashMap<>();
        map.put("foods", maps);
        return map;
    }
}
