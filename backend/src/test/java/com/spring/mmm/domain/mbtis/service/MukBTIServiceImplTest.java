package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import com.spring.mmm.domain.mbtis.service.port.MukBTIQuestionRepository;
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
class MukBTIServiceImplTest {

    @Mock
    private MukBTIQuestionRepository mukBTIQuestionRepository;

    @InjectMocks
    private MukBTIServiceImpl mukBTIService;

    @Test
    void 먹티비아이_질문수신_성공(){

        List<MukBTIQuestionEntity> result = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            result.add(MukBTIQuestionEntity.builder()
                    .questionId(i)
                    .content(Integer.toString(i))
                    .imageSrc(Integer.toString(i))
                    .build());
        }

        BDDMockito.given(mukBTIQuestionRepository.findAllMukBTIQuestion())
                .willReturn(result);

        assertEquals(10, mukBTIService.findAllMukBTIQuestion().size());
    }
}