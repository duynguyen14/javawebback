package com.example.back.Exception;

import com.example.back.DTO.Response.APIResponse;
import com.example.back.Enums.ErrorCodes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<APIResponse> handlerAppException(AppException appException){
        ErrorCodes errorCodes =appException.errorCodes;
        APIResponse apiResponse =new APIResponse();
        apiResponse.setCode(errorCodes.getCode());
        apiResponse.setMessage(errorCodes.getMessage());
        return ResponseEntity.status(errorCodes.getStatus()).body(apiResponse);
    }
}
