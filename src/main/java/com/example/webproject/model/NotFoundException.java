package com.example.webproject.model;

import lombok.Getter;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

}
