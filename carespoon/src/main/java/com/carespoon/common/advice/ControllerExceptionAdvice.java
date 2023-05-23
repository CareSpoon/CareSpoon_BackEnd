package com.carespoon.common.advice;

import com.carespoon.common.dto.ApiResponseDto;
import com.carespoon.exception.ErrorStatus;
import com.carespoon.exception.model.NotMealException;
import com.carespoon.exception.model.NotMenuException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotMenuException.class)
    protected ApiResponseDto handleNotMenuFoundException(final NotMenuException e){
        return ApiResponseDto.error(ErrorStatus.NOT_MENU_FIND_EXCEPTION, ErrorStatus.NOT_MENU_FIND_EXCEPTION.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotMealException.class)
    protected ApiResponseDto handleNotMealFoundException(final NotMealException e){
        return ApiResponseDto.error(ErrorStatus.NOT_MEAL_FIND_EXCEPTION, ErrorStatus.NOT_MEAL_FIND_EXCEPTION.getMessage());
    }
}
