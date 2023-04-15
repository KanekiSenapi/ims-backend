package pl.aogiri.ims.common.converter;

import org.springframework.stereotype.Component;

public abstract class BaseConverter<ENTITY, DTO> {

    public abstract DTO toDto(ENTITY entity);

    public abstract ENTITY toEntity(DTO dto);
}
