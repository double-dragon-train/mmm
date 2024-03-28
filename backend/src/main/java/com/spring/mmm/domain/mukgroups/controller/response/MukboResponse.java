package com.spring.mmm.domain.mukgroups.controller.response;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukboResponse {
    private MukboType type;
    private String name;
    private String mukBTI;
    private MBTI mbti;

    public static MukboResponse create(MukboEntity mukbo){
        return MukboResponse.builder()
                .name(mukbo.getName())
                .type(mukbo.getType())
                .mukBTI(calcMukBTI(MBTI.create(mukbo.getMukBTIResultEntities())))
                .mbti(MBTI.create(mukbo.getMukBTIResultEntities()))
                .build();
    }

    private static String calcMukBTI(MBTI mbti){
        char[] res = new char[4];

        res[0] = mbti.getEI() > 17 ? 'E' : 'I';
        res[1] = mbti.getNS() > 15 ? 'S' : 'N';
        res[2] = mbti.getTF() > 15 ? 'F' : 'T';
        res[3] = mbti.getJP() > 15 ? 'J' : 'P';

        return String.copyValueOf(res);
    }
}
