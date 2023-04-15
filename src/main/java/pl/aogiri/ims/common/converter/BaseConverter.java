package pl.aogiri.ims.common.converter;

public abstract class BaseConverter<ENTITY, DTO> {

    public abstract DTO toDto(ENTITY entity);

    public abstract ENTITY toEntity(DTO dto);
}
