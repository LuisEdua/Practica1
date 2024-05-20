package com.example.ordenesservice.Ordenes.Infrestructure.Exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
