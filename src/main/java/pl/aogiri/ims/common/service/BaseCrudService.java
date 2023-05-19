package pl.aogiri.ims.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import pl.aogiri.ims.common.converter.BaseConverter;
import pl.aogiri.ims.common.repository.BaseCrudRepository;
import pl.aogiri.ims.common.repository.BaseSpecifications;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseCrudService<Entity, UpsertRequest, DetailsResponse, BasicResponse, Criteria> {

    protected final BaseCrudRepository<Entity> repository;
    protected final BaseConverter<Entity, UpsertRequest, DetailsResponse, BasicResponse> converter;
    protected final BaseSpecifications<Entity, Criteria> specifications;

    public List<BasicResponse> find(Criteria filterCriteria) {
        if (filterCriteria == null) {
            return findAllWithoutCriteria();
        } else {
            return findAllByCriteria(filterCriteria);
        }
    }

    private List<BasicResponse> findAllByCriteria(Criteria filterCriteria) {
        Specification<Entity> specification = specifications.filterByCriteria(filterCriteria);
        return repository.findAll(specification).stream()
                .map(converter::toBasicResponse)
                .toList();
    }

    private List<BasicResponse> findAllWithoutCriteria() {
        return repository.findAll().stream()
                .map(converter::toBasicResponse)
                .toList();
    }

    public Optional<DetailsResponse> findById(final UUID id) {
        return repository.findById(id).map(converter::toDetailsResponse);
    }

    public DetailsResponse create(final UpsertRequest dto) {
        Entity entity = converter.toEntity(dto);
        Entity savedEntity = repository.save(entity);
        return converter.toDetailsResponse(savedEntity);
    }

    @Transactional
    public DetailsResponse update(final UUID id, final UpsertRequest dto) {
        Entity entity = converter.toEntity(dto, id);
        Entity savedEntity = repository.save(entity);
        return converter.toDetailsResponse(savedEntity);
    }

    public void deleteById(final UUID id) {
        repository.deleteById(id);
    }
}
