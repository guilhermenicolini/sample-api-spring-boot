package com.github.guilhermenicolini.sampleapispringboot.exceptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message, Object... args) {
        super(String.format(message, args));
    }
}