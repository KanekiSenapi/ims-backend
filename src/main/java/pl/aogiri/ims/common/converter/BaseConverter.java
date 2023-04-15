package pl.aogiri.ims.common.converter;

import java.util.UUID;

public abstract class BaseConverter<Entity, UpsertRequest, DetailsResponse, BasicResponse> {

    public Entity toEntity(UpsertRequest dto) {
        return toEntity(dto, null);
    }

    public abstract Entity toEntity(UpsertRequest dto, UUID id);

    public abstract DetailsResponse toDetailsResponse(Entity entity);

    public abstract BasicResponse toBasicResponse(Entity entity);


}
