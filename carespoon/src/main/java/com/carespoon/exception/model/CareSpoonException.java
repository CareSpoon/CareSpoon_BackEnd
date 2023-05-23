package com.carespoon.exception.model;

import com.carespoon.exception.ErrorStatus;
import lombok.Getter;

@Getter
public class CareSpoonException extends RuntimeException{

    private final ErrorStatus error;

    public CareSpoonException(ErrorStatus error, String message){
        super(message);
        this.error = error;
    }

}
