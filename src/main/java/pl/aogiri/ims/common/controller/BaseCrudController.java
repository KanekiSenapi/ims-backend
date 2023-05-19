package pl.aogiri.ims.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


public interface BaseCrudController<UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria> {

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Create a new resource",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created", content = @Content(schema = @Schema(implementation = Object.class)))
            }
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    DetailsResponse create(@Valid @RequestBody final UpsertRequest dto);

    @GetMapping(
            path = "{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Get a resource by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource found", content = @Content(schema = @Schema(implementation = Object.class))),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    DetailsResponse getById(@PathVariable @Parameter(description = "Resource ID") final UUID id);

    @GetMapping(
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Get all resources",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resources found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Object.class))))
            }
    )
    List<BasicResponse> getAll(@Nullable FilterCriteria filterCriteria);

    @PutMapping(
            path = "{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(
            summary = "Update a resource by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource updated", content = @Content(schema = @Schema(implementation = Object.class))),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    DetailsResponse update(@PathVariable @Parameter(description = "Resource ID") final UUID id, @Valid @RequestBody final UpsertRequest dto);

    @DeleteMapping(
            path = "{id}",
            consumes = {MediaType.ALL_VALUE}
    )
    @Operation(
            summary = "Delete a resource by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Resource deleted"),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    void delete(@PathVariable @Parameter(description = "Resource ID") final UUID id);

}
