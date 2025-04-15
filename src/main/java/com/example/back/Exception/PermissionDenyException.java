package com.example.back.Exception;

public class PermissionDenyException extends Exception{
    public PermissionDenyException(String message) {
        super(message);
    }
}
