package com.spring.mmm.domain.mukjuks.service;

import com.spring.mmm.common.event.Events;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.exception.MukboErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukboException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.mukjuks.controller.response.MukjukResponse;
import com.spring.mmm.domain.mukjuks.domain.FoodMukjukLevel;
import com.spring.mmm.domain.mukjuks.domain.MukgroupMukjukEntity;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import com.spring.mmm.domain.mukjuks.event.FoodRecordedEvent;
import com.spring.mmm.domain.mukjuks.event.MukjukAchievedEvent;
import com.spring.mmm.domain.mukjuks.exception.MukjukErrorCode;
import com.spring.mmm.domain.mukjuks.exception.MukjukException;
import com.spring.mmm.domain.mukjuks.service.port.MukGroupMukjukRepository;
import com.spring.mmm.domain.mukjuks.service.port.MukjukRepository;
import com.spring.mmm.domain.recommends.domain.FoodCategory;
import com.spring.mmm.domain.recommends.domain.RecommendedFoodEntity;
import com.spring.mmm.domain.recommends.service.port.RecommendedFoodRepository;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.spring.mmm.domain.mukjuks.exception.MukjukErrorCode.MUKJUK_NOT_MATCHED;

@RequiredArgsConstructor
@Service
public class MukjukServiceImpl implements MukjukService {
    private final MukjukRepository mukjukRepository;
    private final MukgroupRepository mukgroupRepository;
    private final MukboRepository mukboRepository;
    private final RecommendedFoodRepository recommendedFoodRepository;
    private final MukGroupMukjukRepository mukGroupMukjukRepository;

    @Override
    public MukjukResponse findAllMukjuks(Long groupId, UserDetailsImpl users) {
        // groupId 검증
        MukgroupEntity mukgroup = validateFindAllMukjuks(groupId, users);
        return MukjukResponse.create(mukgroup, mukjukRepository.findAllBadges(groupId));
    }

    @Override
    public void handleFoodRecordedEvent(FoodRecordedEvent event) {
        RecommendedFoodEntity recommendedFood = recommendedFoodRepository.findByRecommendedFoodId(event.getFoodRecommendedId())
                .orElseThrow(() -> new MukjukException(MukjukErrorCode.RECOMMENDED_FOOD_NOT_FOUND));
        FoodCategory recordedCategory = recommendedFood.getFoodEntity().getFoodCategoryEntity().getName();
        // 먹적 목록 불러오기

        MukgroupEntity mukgroup = recommendedFood.getFoodRecommendEntity().getMukgroupEntity();
        // 이제 매칭해서 깼는지 못걨는지 ㄱㄱ
        List<MukjukEntity> unclearedMukjuk = mukjukRepository.findUnclearedMukjuk(mukgroup.getMukgroupId());
        if (unclearedMukjuk.isEmpty()) {
            return;
        }
        Integer count = mukgroupRepository.countMukusByFoodCategory(mukgroup.getMukgroupId(), recordedCategory);
        MukjukMatchingResult matchingResult = matchMukjuk(unclearedMukjuk, recordedCategory);
        if (canClear(count, matchingResult)) {
            clearMukjuk(matchingResult, mukgroup);
        }
    }

    private void clearMukjuk(MukjukMatchingResult matchingResult, MukgroupEntity mukgroup) {
        MukjukEntity selectedMukjuk = matchingResult.getSelectedMukjuk();
        mukGroupMukjukRepository.save(MukgroupMukjukEntity.create(mukgroup, selectedMukjuk));
        // 이벤트 발행!
        Events.raise(new MukjukAchievedEvent(selectedMukjuk.getName(), mukgroup.getMukgroupId()));
    }

    private boolean canClear(Integer count, MukjukMatchingResult matchingResult) {
        return count >= matchingResult.getSelectedLevel().getCondition();
    }

    private MukjukMatchingResult matchMukjuk(List<MukjukEntity> unclearedMukjuk, FoodCategory recordedCategory) {
        for (FoodMukjukLevel level : FoodMukjukLevel.values()) {
            for (MukjukEntity mukjuk : unclearedMukjuk) {
                String mukjukName = combineMukjukName(level, recordedCategory);
                if (!mukjuk.getName().equals(mukjukName))
                    continue;
                return new MukjukMatchingResult(level, mukjuk);
            }
        }
        throw new MukjukException(MUKJUK_NOT_MATCHED);
    }

    private String combineMukjukName(FoodMukjukLevel level, FoodCategory recordedCategory) {
        return recordedCategory.getKoreanName() +
                " " +
                level.getTitle();
    }

    private MukgroupEntity validateFindAllMukjuks(Long groupId, UserDetailsImpl users) {
        MukgroupEntity mukgroup = mukgroupRepository.findByMukgroupId(groupId)
                .orElseThrow(() -> new MukGroupException(MukGroupErrorCode.NOT_FOUND));
        // user조회하고, mukbo가 group에 속한지 검증
        if (mukgroup.getIsSolo()) {
            throw new MukGroupException(MukGroupErrorCode.SOLO_CANT_ACCESS_MUKJUK);
        }
        MukboEntity mukbo = mukboRepository.findByUserId(users.getUser().getId())
                .orElseThrow(() -> new MukboException(MukboErrorCode.NOT_FOUND));
        if (!mukbo.getMukgroupEntity().equals(mukgroup)) {
            throw new MukGroupException(MukGroupErrorCode.FORBIDDEN);
        }
        return mukgroup;
    }

    @RequiredArgsConstructor
    @Getter
    private static class MukjukMatchingResult {
        private final FoodMukjukLevel selectedLevel;
        private final MukjukEntity selectedMukjuk;

    }
}
