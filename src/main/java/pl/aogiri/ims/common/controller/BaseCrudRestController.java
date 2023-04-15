package pl.aogiri.ims.common.controller;

import lombok.RequiredArgsConstructor;
import pl.aogiri.ims.common.exception.NotFoundException;
import pl.aogiri.ims.common.service.BaseCrudService;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.customer.presentation.dto.CustomerDTO;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseCrudRestController<ENTITY, DTO> implements BaseCrudController<DTO> {

    private final BaseCrudService<ENTITY, DTO> crudService;

    @Override
    public DTO create(final DTO dto) {
        return crudService.create(dto);
    }

    @Override
    public DTO getById(final UUID id) {
        return crudService.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<DTO> getAll() {
        return crudService.findAll();
    }

    @Override
    public DTO update(final UUID id, final DTO dto) {
        return crudService.update(dto);
    }

    @Override
    public void delete(final UUID id) {
        crudService.deleteById(id);
    }
}
