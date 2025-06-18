package com.example.mapping.exception;

public class DuplicatePhoneException extends RuntimeException{
    public DuplicatePhoneException() {
        super("Phone number already exists");
    }
}
