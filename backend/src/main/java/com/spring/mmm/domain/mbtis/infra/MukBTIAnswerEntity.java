package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIAnswer;
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

    public static MukBTIAnswerEntity from(MukBTIAnswer mukBTIAnswer){
        return MukBTIAnswerEntity.builder()
                .answerId(mukBTIAnswer.getAnswerId())
                .content(mukBTIAnswer.getContent())
                .score(mukBTIAnswer.getScore())
                .imageSrc(mukBTIAnswer.getImageSrc())
                .build();
    }

    public MukBTIAnswer to(){
        return MukBTIAnswer.builder()
                .answerId(this.answerId)
                .content(this.content)
                .score(this.score)
                .imageSrc(this.imageSrc)
                .mukbtiQuestionId(this.mukBTIQuestionEntity.getQuestionId())
                .build();
    }
}
