package com.example.back.Exception;

import com.example.back.Enums.ErrorCodes;

public class AppException extends RuntimeException{
    ErrorCodes errorCodes;


    public AppException(ErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.errorCodes=errorCodes;
    }
}
