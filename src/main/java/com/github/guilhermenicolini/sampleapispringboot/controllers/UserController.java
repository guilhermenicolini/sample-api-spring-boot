package com.github.guilhermenicolini.sampleapispringboot.controllers;

import com.github.guilhermenicolini.sampleapispringboot.business.UserBusiness;
import com.github.guilhermenicolini.sampleapispringboot.controllers.handlers.ResponseError;
import com.github.guilhermenicolini.sampleapispringboot.dto.CreateUserDto;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "End points to maintain users")
public class UserController {

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "User sign up", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", headers = {
                    @Header(name = "Authorization", description = "Bearer authentication token", schema = @Schema(type = "string"))
            }),
            @ApiResponse(responseCode = "400", description = "Business errors", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            ))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity<Void> create(
            HttpServletResponse response,
            @Parameter(required = true,
                    description = "Payload to create an user")
            @Valid @RequestBody CreateUserDto payload) {

        String id = userBusiness.signUp(payload);
        String token = jwtUtil.generateToken(id, payload.getEmail(), payload.getName());

        response.addHeader("access-control-expose-headers", "Authorization, Location");
        response.addHeader("Authorization", "Bearer " + token);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(uri).build();
    }
}
