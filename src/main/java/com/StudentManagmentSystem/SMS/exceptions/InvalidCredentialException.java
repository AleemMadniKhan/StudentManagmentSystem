package com.StudentManagmentSystem.SMS.exceptions;

public class InvalidCredentialException extends RuntimeException{
    InvalidCredentialException(){
        super("Credentials are Invalid.");
    }
}
