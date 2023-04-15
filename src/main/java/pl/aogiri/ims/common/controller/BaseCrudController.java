package pl.aogiri.ims.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Base CRUD Operations")
public interface BaseCrudController<DTO> {

    @PostMapping
    @Operation(
            summary = "Create a new resource",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created", content = @Content(schema = @Schema(implementation = Object.class)))
            }
    )
    DTO create(@RequestBody final DTO dto);

    @GetMapping(path = "{id}")
    @Operation(
            summary = "Get a resource by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource found", content = @Content(schema = @Schema(implementation = Object.class))),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    DTO getById(@PathVariable @Parameter(description = "Resource ID") final UUID id);

    @GetMapping
    @Operation(
            summary = "Get all resources",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resources found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Object.class))))
            }
    )
    List<DTO> getAll();

    @PutMapping(path = "{id}")
    @Operation(
            summary = "Update a resource by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource updated", content = @Content(schema = @Schema(implementation = Object.class))),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    DTO update(@PathVariable @Parameter(description = "Resource ID") final UUID id, @RequestBody final DTO dto);

    @DeleteMapping(path = "{id}")
    @Operation(
            summary = "Delete a resource by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Resource deleted"),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    void delete(@PathVariable @Parameter(description = "Resource ID") final UUID id);

}
