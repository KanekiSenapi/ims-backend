package pl.aogiri.ims.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.aogiri.ims.common.converter.BaseConverter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseCrudService<Entity, UpsertRequest, DetailsResponse, BasicResponse> {

    protected final JpaRepository<Entity, UUID> repository;
    protected final BaseConverter<Entity, UpsertRequest, DetailsResponse, BasicResponse> converter;

    public List<BasicResponse> findAll() {
        return repository.findAll().stream()
                .map(converter::toBasicResponse)
                .toList();
    }

    public Optional<DetailsResponse> findById(final UUID id) {
        return repository.findById(id)
                .map(converter::toDetailsResponse);
    }

    public DetailsResponse create(final UpsertRequest dto) {
        Entity entity = converter.toEntity(dto);
        Entity savedEntity = repository.save(entity);
        return converter.toDetailsResponse(savedEntity);
    }

    public DetailsResponse update(final UUID id, final UpsertRequest dto) {
        Entity entity = converter.toEntity(dto, id);
        Entity savedEntity = repository.save(entity);
        return converter.toDetailsResponse(savedEntity);
    }

    public void deleteById(final UUID id) {
        repository.deleteById(id);
    }
}
