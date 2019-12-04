package com.github.guilhermenicolini.sampleapispringboot.dto;

import com.github.guilhermenicolini.sampleapispringboot.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(name = "Login", description = "Login payload for authentication")
public class LoginDto {

    @Schema(required = true, format = "email")
    @NotNull(message = "Email is required")
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "Invalid email")
    private String email;

    @Schema(required = true)
    @NotBlank(message = "Password is required")
    private String password;

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
}