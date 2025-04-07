package com.example.back.Enums;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCodes {

    INTERNAL_SERVER(9999,"undefine error",HttpStatus.INTERNAL_SERVER_ERROR),

    ;

    ErrorCodes(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    int code;
    String message;
    HttpStatus status;

}
