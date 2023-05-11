package pl.aogiri.ims.common.controller;

import lombok.RequiredArgsConstructor;
import pl.aogiri.ims.common.exception.NotFoundException;
import pl.aogiri.ims.common.service.BaseCrudService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseCrudRestController<Entity, UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria>
        implements BaseCrudController<UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria> {

    protected final BaseCrudService<Entity, UpsertRequest, DetailsResponse, BasicResponse, FilterCriteria> crudService;

    @Override
    public DetailsResponse create(final UpsertRequest dto) {
        return crudService.create(dto);
    }

    @Override
    public DetailsResponse getById(final UUID id) {
        return crudService.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<BasicResponse> getAll(final FilterCriteria filterCriteria) {
        return crudService.find(filterCriteria);
    }

    @Override
    public DetailsResponse update(final UUID id, final UpsertRequest dto) {
        return crudService.update(id, dto);
    }

    @Override
    public void delete(final UUID id) {
        crudService.deleteById(id);
    }
}
