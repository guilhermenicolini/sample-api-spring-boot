package com.github.guilhermenicolini.sampleapispringboot.controllers;

import com.github.guilhermenicolini.sampleapispringboot.business.AuthBusiness;
import com.github.guilhermenicolini.sampleapispringboot.controllers.handlers.ResponseError;
import com.github.guilhermenicolini.sampleapispringboot.dto.LoginDto;
import com.github.guilhermenicolini.sampleapispringboot.dto.UserDto;
import com.github.guilhermenicolini.sampleapispringboot.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "auth", description = "Authentication operations")
public class AuthController {

    @Autowired
    private AuthBusiness authBusiness;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Log user into the system", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User authenticated", headers = {
                    @Header(name = "Authorization", description = "Bearer authentication token", schema = @Schema(type = "string"))
            }),
            @ApiResponse(responseCode = "400", description = "Business errors", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            ))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/login", consumes = { "application/json" }, produces = { "application/json" } )
    public ResponseEntity<Void> login(
            @Parameter(required = true,
                    description = "Login payload for authentication")
            @Valid @RequestBody LoginDto payload,
            HttpServletResponse response) {

        UserDto user = authBusiness.authenticate(payload);
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getName());

        response.addHeader("access-control-expose-headers", "Authorization");
        response.addHeader("Authorization", "Bearer " + token);

        return ResponseEntity.noContent().build();
    }
}
