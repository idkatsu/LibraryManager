package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class ResourseNotFoundException extends BaseException {
    public ResourseNotFoundException(String resourceName) {
        super(resourceName, HttpStatus.NOT_FOUND);
    }
}
