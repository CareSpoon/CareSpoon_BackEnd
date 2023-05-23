package com.carespoon.common.dto;

import com.carespoon.exception.ErrorStatus;
import com.carespoon.exception.Success;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseDto <T>{

    private final int code;
    private final String message;
    private T data;


    public static <T> ApiResponseDto<T> success(Success success, T data) {
        return new ApiResponseDto<>(success.getHttpStatusCode(), "요청이 성공적으로 완료되었습니다.", data);
    }

    public static ApiResponseDto error(ErrorStatus error, String message) {
        return new ApiResponseDto<>(error.getHttpStatusCode(), message);
    }
}
