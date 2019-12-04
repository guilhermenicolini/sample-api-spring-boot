package com.github.guilhermenicolini.sampleapispringboot.dto;

import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Login", description = "User")
public class UserDto {

    @Schema
    private String id;

    @Schema
    private String email;

    @Schema
    private String name;

    public UserDto() {}

    public UserDto(User obj) {
        setId(obj.getId());
        setEmail(obj.getEmail());
        setName(obj.getName());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}