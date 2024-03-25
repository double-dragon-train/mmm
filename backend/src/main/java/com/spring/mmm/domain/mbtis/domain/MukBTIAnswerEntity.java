package com.spring.mmm.domain.mbtis.domain;

import com.spring.mmm.domain.mbtis.controller.response.AnswerResponse;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "answer")
@Entity
public class MukBTIAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer answerId;

    @Column(name = "content")
    private String content;

    @Column(name = "score")
    private Integer score;

    @Column(name = "image_src")
    private String imageSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private MukBTIQuestionEntity mukBTIQuestionEntity;

    public AnswerResponse toAnswer(){
        return AnswerResponse.builder()
                .answerId(this.answerId)
                .answerContext(this.content)
                .answerImage(this.imageSrc != null ? this.imageSrc : null)
                .build();
    }
}
