package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIQuestion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question")
@Entity
public class MukBTIQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "image_src")
    private String imageSrc;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "mukBTIQuestionEntity", cascade = CascadeType.REMOVE)
    private List<MukBTIAnswerEntity> mukBTIAnswerEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbti_id")
    private MukBTIEntity mukBTIEntity;

    public static MukBTIQuestionEntity from(MukBTIQuestion mukBTIQuestion){
        return MukBTIQuestionEntity.builder()
                .questionId(mukBTIQuestion.getQuestionId())
                .imageSrc(mukBTIQuestion.getImageSrc())
                .content(mukBTIQuestion.getContent())
                .build();
    }

    public MukBTIQuestion to(){
        return MukBTIQuestion.builder()
                .questionId(this.questionId)
                .imageSrc(this.imageSrc)
                .content(this.content)
                .mukbtiId(this.mukBTIEntity.getMukbtiId())
                .build();
    }
}
