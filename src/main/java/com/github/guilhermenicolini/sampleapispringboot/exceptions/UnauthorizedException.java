package com.github.guilhermenicolini.sampleapispringboot.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

    public UnauthorizedException(String message, Object... args) {
        super(String.format(message, args));
    }
}