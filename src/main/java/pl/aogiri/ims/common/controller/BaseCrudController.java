package pl.aogiri.ims.common.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface BaseCrudController<DTO> {

    @PostMapping
    DTO create(final DTO dto);

    @GetMapping(path = "{id}")
    DTO getById(@PathVariable final UUID id);

    @GetMapping
    List<DTO> getAll();

    @PutMapping(path = "{id}")
    DTO update(@PathVariable final UUID id, @RequestBody final DTO dto);

    @DeleteMapping(path = "{id}")
    void delete(@PathVariable final UUID id);
}
