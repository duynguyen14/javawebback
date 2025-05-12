package com.example.back.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCodes {

    UNCATEGORIZED_EXCEPTION(9999,"undefined error",HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001,"user is existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002,"user not found", HttpStatus.NOT_FOUND),
    USER_NAME_OR_PASSWORD_INCORRECT(1003, "user name or password incorrect", HttpStatus.BAD_REQUEST),

    UNAUTHENTICATED(2000,"unauthorized", HttpStatus.UNAUTHORIZED),

    UNAUTHORIZED(2001, "you do not have permission",HttpStatus.FORBIDDEN),
    ROLE_NOT_FOUND(1111, "role not found", HttpStatus.NOT_FOUND),

    ;

    ErrorCodes(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
    private final int code;
    private final String message;
    private final HttpStatus status;
}
