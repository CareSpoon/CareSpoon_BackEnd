package com.carespoon.exception.model;

import com.carespoon.exception.ErrorStatus;

public class NotMenuException extends CareSpoonException{
    public NotMenuException(ErrorStatus error, String message){
        super(error, message);
    }

}
