package pl.aogiri.ims.product.domain.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.converter.BaseConverter;
import pl.aogiri.ims.customer.domain.converter.CustomerConverter;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.product.domain.entity.ProductEntity;
import pl.aogiri.ims.product.presentation.dto.ProductBasicResponse;
import pl.aogiri.ims.product.presentation.dto.ProductDetailsResponse;
import pl.aogiri.ims.product.presentation.dto.ProductUpsertRequest;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ProductConverter extends BaseConverter<ProductEntity, ProductUpsertRequest, ProductDetailsResponse, ProductBasicResponse> {

    private final CustomerConverter customerConverter;

    @Override
    public ProductEntity toEntity(final ProductUpsertRequest request, final UUID id) {
        return ProductEntity.builder()
                .id(id)
                .name(request.getName())
                .netUnitPrice(request.getNetUnitPrice())
                .customer(new CustomerEntity(request.getCustomerId(), null, null, null))
                .build();
    }

    @Override
    public ProductDetailsResponse toDetailsResponse(final ProductEntity productEntity) {
        return ProductDetailsResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .netUnitPrice(productEntity.getNetUnitPrice())
                .customer(customerConverter.toBasicResponse(productEntity.getCustomer()))
                .build();
    }

    @Override
    public ProductBasicResponse toBasicResponse(final ProductEntity productEntity) {
        return ProductBasicResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .build();
    }
}
