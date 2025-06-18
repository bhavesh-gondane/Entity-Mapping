package com.example.mapping.exception;

public class DuplicateProjectNameException extends RuntimeException{
    public DuplicateProjectNameException(){
        super("This project department name already exists");
    }
}
