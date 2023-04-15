package pl.aogiri.ims.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.aogiri.ims.common.converter.BaseConverter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseCrudService<ENTITY, DTO> {

    protected final JpaRepository<ENTITY, UUID> repository;
    protected final BaseConverter<ENTITY, DTO> converter;

    public List<DTO> findAll() {
        return repository.findAll().stream()
                .map(converter::toDto)
                .toList();
    }

    public Optional<DTO> findById(final UUID id) {
        return repository.findById(id)
                .map(converter::toDto);
    }

    public DTO create(final DTO dto) {
        ENTITY entity = converter.toEntity(dto);
        ENTITY savedEntity = repository.save(entity);
        return converter.toDto(savedEntity);
    }

    public DTO update(final DTO dto) {
        ENTITY entity = converter.toEntity(dto);
        ENTITY savedEntity = repository.save(entity);
        return converter.toDto(savedEntity);
    }

    public void deleteById(final UUID id) {
        repository.deleteById(id);
    }
}
