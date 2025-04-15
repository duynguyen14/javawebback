package com.example.back.exception;

import com.example.back.dtos.APIResponse;
import com.example.back.enums.ErrorCodes;
import com.example.back.responses.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Chi dinh cho lop xu li ngoai le chung
//@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<APIResponse> handlerAppException(AppException appException){
        ErrorCodes errorCodes =appException.errorCodes;
        APIResponse apiResponse =new APIResponse();
        apiResponse.setCode(errorCodes.getCode());
        apiResponse.setMessage(errorCodes.getMessage());
        return ResponseEntity.status(errorCodes.getStatus()).body(apiResponse);
    }
    // moi cho
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObject> handleGeneralException(Exception exception) {
        return ResponseEntity.internalServerError().body(
                ResponseObject.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(exception.getMessage())
                        .build()
        );
    }
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(DataNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseObject.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build());
    }


}
