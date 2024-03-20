package com.spring.mmm.domain.mbtis.domain;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MBTI {
    private Integer EI;
    private Integer NS;
    private Integer TF;
    private Integer JP;
    private Integer Mint;
    private Integer Pine;
    private Integer Die;

    public static MBTI create(List<MukBTIResultEntity> mukBTIResultEntities){
        MBTIBuilder mbtiBuilder = new MBTIBuilder();
        for(MukBTIResultEntity mukBTIResult : mukBTIResultEntities){
            switch (mukBTIResult.getMukBTIEntity().getType()){
                case MukBTIType.EI -> mbtiBuilder.EI(mukBTIResult.getScore());
                case MukBTIType.NS -> mbtiBuilder.NS(mukBTIResult.getScore());
                case MukBTIType.TF -> mbtiBuilder.TF(mukBTIResult.getScore());
                case MukBTIType.JP -> mbtiBuilder.JP(mukBTIResult.getScore());
                case MukBTIType.MINT -> mbtiBuilder.Mint(mukBTIResult.getScore());
                case MukBTIType.PINE -> mbtiBuilder.Pine(mukBTIResult.getScore());
                case MukBTIType.DIE -> mbtiBuilder.Die(mukBTIResult.getScore());
            }
        }
        return mbtiBuilder.build();
    }
}
