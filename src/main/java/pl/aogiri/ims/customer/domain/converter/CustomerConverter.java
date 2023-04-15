package pl.aogiri.ims.customer.domain.converter;

import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.converter.BaseConverter;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.customer.presentation.dto.CustomerBasicResponse;
import pl.aogiri.ims.customer.presentation.dto.CustomerDetailsResponse;
import pl.aogiri.ims.customer.presentation.dto.CustomerUpsertRequest;

import java.util.UUID;

@Component
public class CustomerConverter extends BaseConverter<CustomerEntity, CustomerUpsertRequest, CustomerDetailsResponse, CustomerBasicResponse> {

    @Override
    public CustomerEntity toEntity(final CustomerUpsertRequest request, final UUID id) {
        return CustomerEntity.builder()
                .id(id)
                .name(request.getName())
                .krs(request.getKrs())
                .nip(request.getNip())
                .regon(request.getRegon())
                .address(request.getAddress())
                .build();
    }

    @Override
    public CustomerDetailsResponse toDetailsResponse(final CustomerEntity customerEntity) {
        return CustomerDetailsResponse.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .krs(customerEntity.getKrs())
                .nip(customerEntity.getNip())
                .regon(customerEntity.getRegon())
                .address(customerEntity.getAddress())
                .build();
    }

    @Override
    public CustomerBasicResponse toBasicResponse(final CustomerEntity customerEntity) {
        return CustomerBasicResponse.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .build();
    }
}
