package pl.aogiri.ims.customer.domain.converter;

import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.converter.BaseConverter;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.customer.presentation.dto.CustomerDTO;

@Component
public class CustomerConverter extends BaseConverter<CustomerEntity,  CustomerDTO> {

    public CustomerDTO toDto(CustomerEntity customerEntity) {
        return CustomerDTO.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .krs(customerEntity.getKrs())
                .nip(customerEntity.getNip())
                .regon(customerEntity.getRegon())
                .address(customerEntity.getAddress())
                .build();
    }

    public CustomerEntity toEntity(CustomerDTO customerDto) {
        return CustomerEntity.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .krs(customerDto.getKrs())
                .nip(customerDto.getNip())
                .regon(customerDto.getRegon())
                .address(customerDto.getAddress())
                .build();
    }
}
