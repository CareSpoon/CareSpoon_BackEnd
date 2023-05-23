package com.carespoon.exception.model;

import com.carespoon.exception.ErrorStatus;

public class NotMealException extends CareSpoonException {
    public NotMealException(ErrorStatus error, String message){
        super(error, message);
    }
}
