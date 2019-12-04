package com.github.guilhermenicolini.sampleapispringboot.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message, Object... args) {
        super(String.format(message, args));
    }
}