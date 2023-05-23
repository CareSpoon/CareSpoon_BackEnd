package com.carespoon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Success {
    MENU_FIND_SUCCESS(HttpStatus.ACCEPTED, "메뉴가 성공적으로 조회되었습니다."),
    ;
    private final HttpStatus httpStatus;
    private final String message;
    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
