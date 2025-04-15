package com.example.back.Exception;

public class ExpiredTokenException extends Exception{
    public ExpiredTokenException(String message) {
        super(message);
    }
}
