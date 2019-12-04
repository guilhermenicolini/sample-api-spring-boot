package com.github.guilhermenicolini.sampleapispringboot.controllers.handlers;

import com.github.guilhermenicolini.sampleapispringboot.exceptions.BusinessException;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.ForbiddenException;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //Custom errors
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error(ex.getMessage(), ex);

        //In this moment, this error occurs when there is no body in request or when
        //the value does not exists in enum type

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError("Required request body is missing or invalid"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(
                        ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.toList())
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> exception(Exception ex) {
        logger.error(ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseError("An error occurred while processing your request"));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseError> businessException(BusinessException ex) {
        logger.error(ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseError> unauthorizedException(UnauthorizedException ex) {
        logger.error(ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseError(ex.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ResponseError> forbiddenException(ForbiddenException ex) {
        logger.error(ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ResponseError(ex.getMessage()));
    }
}
