package com.carespoon.common.advice;

import com.carespoon.common.dto.ApiResponseDto;
import com.carespoon.exception.ErrorStatus;
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
        return ApiResponseDto.error(ErrorStatus.NOT_MENU_FIND_EXCEPTION);
    }
}
