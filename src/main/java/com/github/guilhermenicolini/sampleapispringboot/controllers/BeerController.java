package com.github.guilhermenicolini.sampleapispringboot.controllers;

import com.github.guilhermenicolini.sampleapispringboot.business.BeerBusiness;
import com.github.guilhermenicolini.sampleapispringboot.controllers.handlers.ResponseError;
import com.github.guilhermenicolini.sampleapispringboot.dto.BeerDto;
import com.github.guilhermenicolini.sampleapispringboot.dto.BeerSummaryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/beers")
@Tag(name = "Beer", description = "End points to maintain beers")
public class BeerController {

    @Autowired
    private BeerBusiness beerBusiness;

    @Operation(summary = "Retrieve user beers",
            method = "GET",
            security = {
                @SecurityRequirement(name = "Bearer")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Business errors", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            ))
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = { "application/json" })
    public ResponseEntity<List<BeerSummaryDto>> getAll() {
        return ResponseEntity.ok(beerBusiness.getAll());
    }

    @Operation(summary = "Retrieve an user beer",
            method = "GET",
            security = {
                    @SecurityRequirement(name = "Bearer")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Business errors", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            ))
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = { "application/json" })
    public ResponseEntity<BeerDto> get(
            @PathVariable
            @Parameter(description = "Beer id", required = true)
            String id) {
        return ResponseEntity.ok(beerBusiness.get(id));
    }

    @Operation(summary = "Create an user beer",
            method = "POST",
            security = {
                    @SecurityRequirement(name = "Bearer")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", headers = {
                    @Header(name = "Location", description = "Created beer location", schema = @Schema(type = "string"))
            }),
            @ApiResponse(responseCode = "400", description = "Business errors", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(
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
                    description = "Payload to create a beer")
            @Valid
            @RequestBody
            BeerDto payload) {

        String id = beerBusiness.create(payload);

        response.addHeader("access-control-expose-headers", "Location");

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Update an user beer",
            method = "PUT",
            security = {
                    @SecurityRequirement(name = "Bearer")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Beer updated"),
            @ApiResponse(responseCode = "400", description = "Business errors", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            ))

    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity<Void> update(
            @Parameter(description = "User id", required = true)
            @PathVariable
            String id,
            @Parameter(required = true,
                    description = "Payload to update a beer")
            @Valid
            @RequestBody
            BeerDto payload) {

        payload.setId(id);
        beerBusiness.update(payload);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete an user beer",
            method = "DELETE",
            security = {
                    @SecurityRequirement(name = "Bearer")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Beer deleted"),
            @ApiResponse(responseCode = "400", description = "Business errors", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            )),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
                    schema = @Schema(implementation = ResponseError.class)
            ))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = { "application/json" })
    public ResponseEntity<Void> delete(
            @Parameter(description = "User id", required = true)
            @PathVariable String id) {
        beerBusiness.delete(id);
        return ResponseEntity.noContent().build();
    }
}
