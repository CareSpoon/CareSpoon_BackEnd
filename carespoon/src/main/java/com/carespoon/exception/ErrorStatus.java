package com.carespoon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    NOT_MENU_FIND_EXCEPTION(HttpStatus.BAD_REQUEST,"메뉴가 탐지되지 않았습니다."),
    NOT_MEAL_FIND_EXCEPTION(HttpStatus.BAD_REQUEST, "해당 날짜에 섭취한 메뉴가 없습니다."),
    ;
    private final HttpStatus httpStatus;
    private final String message;
    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}
