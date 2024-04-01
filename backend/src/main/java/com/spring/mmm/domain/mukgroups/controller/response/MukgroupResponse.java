package com.spring.mmm.domain.mukgroups.controller.response;

import org.springframework.util.StringUtils;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukgroupResponse {
	private Long mukgroupId;
	private String name;
	private Boolean isSolo;
	private String imageSrc;
	private String titleMukjukName;
	private String titleMukjukImage;

	public static MukgroupResponse createByMukgroupEntity(MukgroupEntity mukgroupEntity) {



		return MukgroupResponse.builder()
			.mukgroupId(mukgroupEntity.getMukgroupId())
			.name(mukgroupEntity.getName())
			.isSolo(mukgroupEntity.getIsSolo())
			.imageSrc(mukgroupEntity.getImageSrc())
			.titleMukjukImage(mukgroupEntity.getMukjukEntity() != null
				? mukgroupEntity.getMukjukEntity().getImageSrc() : null)
			.titleMukjukName(mukgroupEntity.getMukjukEntity() != null
				? mukgroupEntity.getMukjukEntity().getName() : null)
			.build();
	}
}
