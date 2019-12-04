package com.github.guilhermenicolini.sampleapispringboot.controllers.handlers;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Schema(name = "Error", description = "Response error payload")
public class ResponseError implements Serializable {

    private List<String> errors = new ArrayList<>();

    public ResponseError(String message) {
        errors.add(message);
    }

    public ResponseError(List<String> errors) {
        this.errors.addAll(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}