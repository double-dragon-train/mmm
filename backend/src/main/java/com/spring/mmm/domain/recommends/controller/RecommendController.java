package com.spring.mmm.domain.recommends.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecommendController {

    @GetMapping("/recommend")
    public Map<String, List<Map<String,String>>> recommendRandomFood(){

        Map<String, List<Map<String,String>>> map = new HashMap<>();
        map.put("foods", List.of(
                Map.of("name","제육","imageSrc","https://goumunity.s3.ap-northeast-2.amazonaws.com/upload/%EC%A0%9C%EC%9C%A1.jfif"),
                Map.of("name","돈까스","imageSrc","https://goumunity.s3.ap-northeast-2.amazonaws.com/upload/%EA%B9%8C%EC%8A%A4.jpg")
        ));
        return map;
    }
}
