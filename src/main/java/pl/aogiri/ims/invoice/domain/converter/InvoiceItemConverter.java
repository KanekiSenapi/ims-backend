package pl.aogiri.ims.invoice.domain.converter;

import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.converter.BaseConverter;
import pl.aogiri.ims.invoice.domain.entity.InvoiceItemEntity;
import pl.aogiri.ims.invoice.presentation.dto.invoiceitem.InvoiceItemBasicResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoiceitem.InvoiceItemDetailsResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoiceitem.InvoiceItemUpsertRequest;

import java.util.UUID;

@Component
public class InvoiceItemConverter
        extends BaseConverter<InvoiceItemEntity, InvoiceItemUpsertRequest, InvoiceItemDetailsResponse, InvoiceItemBasicResponse> {

    @Override
    public InvoiceItemEntity toEntity(final InvoiceItemUpsertRequest request, final UUID id) {
        return InvoiceItemEntity.builder()
                .id(id)
                .build();
    }

    @Override
    public InvoiceItemDetailsResponse toDetailsResponse(final InvoiceItemEntity itemEntity) {
        return InvoiceItemDetailsResponse.builder()
                .id(itemEntity.getId())
                .name(itemEntity.getProduct().getName())
                .quantity(itemEntity.getQuantity())
                .unit(itemEntity.getUnit())
                .tax(itemEntity.getTax())
                .totalNetAmount(itemEntity.getTotalNetAmount())
                .totalGrossAmount(itemEntity.getTotalGrossAmount())
                .build();
    }

    @Override
    public InvoiceItemBasicResponse toBasicResponse(final InvoiceItemEntity itemEntity) {
        return InvoiceItemBasicResponse.builder()
                .id(itemEntity.getId())
                .build();
    }
}
