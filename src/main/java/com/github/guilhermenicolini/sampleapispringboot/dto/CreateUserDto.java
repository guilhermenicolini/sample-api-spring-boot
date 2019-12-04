package com.github.guilhermenicolini.sampleapispringboot.dto;

import com.github.guilhermenicolini.sampleapispringboot.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Schema(name = "CreateUser", description = "Payload to create an user")
public class CreateUserDto {

    @Schema(required = true)
    @NotNull(message = "Email is required")
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "Invalid email")
    private String email;

    @Schema(required = true)
    @NotBlank(message = "Password is required")
    private String password;

    @Schema(required = true)
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
