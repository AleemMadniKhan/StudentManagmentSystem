package com.StudentManagmentSystem.SMS.exceptions;

public class UnauthorizeException extends RuntimeException {
    public UnauthorizeException(String message){
        super(message);
    }
}
