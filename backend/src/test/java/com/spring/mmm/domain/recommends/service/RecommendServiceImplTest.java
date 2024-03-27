package com.spring.mmm.domain.recommends.service;

import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecommendServiceImplTest {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private RecommendServiceImpl recommendService;

    static private List<FoodEntity> foods;

    @BeforeAll
    static void 음식장전(){
        foods = new ArrayList<>();
        for(int i = 1; i <= 97; i++){
            foods.add(FoodEntity.builder()
                    .foodId(i)
                    .name(Integer.toString(i))
                    .image(Integer.toString(i))
                    .build());
        }
    }

    @Test
    void 랜덤음식_일곱개_추천_성공(){
        BDDMockito.given(foodRepository.findAll())
                .willReturn(foods);

        assertEquals(7, recommendService.recommendRandomFood().size());
    }

}