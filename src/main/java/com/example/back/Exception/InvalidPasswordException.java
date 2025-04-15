package com.example.back.Exception;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String message) {
        super(message);
    }
}
