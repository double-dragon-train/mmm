package com.spring.mmm.domain.mukgroups.exception;

import com.spring.mmm.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MukGroupErrorCode implements ErrorCode {
    IMAGE_INVALID(HttpStatus.BAD_REQUEST, "이미지 파일이 올바르지 않습니다."),
    NOT_UPLOADED(HttpStatus.BAD_REQUEST, "이미지 업로드에 실패했습니다."),
    ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 단체 먹그룹이 존재합니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public String getErrorName() {
        return this.name();
    }
}
