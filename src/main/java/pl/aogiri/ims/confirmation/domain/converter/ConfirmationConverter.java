package pl.aogiri.ims.confirmation.domain.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.converter.BaseConverter;
import pl.aogiri.ims.confirmation.domain.entity.ConfirmationEntity;
import pl.aogiri.ims.confirmation.domain.entity.ConfirmationType;
import pl.aogiri.ims.confirmation.presentation.dto.ConfirmationUpsertRequest;
import pl.aogiri.ims.file.domain.value.FileType;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ConfirmationConverter extends BaseConverter<ConfirmationEntity, ConfirmationUpsertRequest, Void, Void> {

    @Override
    public ConfirmationEntity toEntity(final ConfirmationUpsertRequest request, final UUID id) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setId(request.getInvoiceId());

        return ConfirmationEntity.builder()
                .id(id)
                .invoice(invoiceEntity)
                .file(request.getFileLocation().toString())
                .type(mapToConfirmationType(request.getType()))
                .build();
    }

    @Override
    public Void toDetailsResponse(final ConfirmationEntity invoiceEntity) {
        return null;
    }

    @Override
    public Void toBasicResponse(final ConfirmationEntity invoiceEntity) {
        return null;
    }

    private ConfirmationType mapToConfirmationType(FileType fileType) {
        return switch (fileType) {
            case INVOICE -> throw new UnsupportedOperationException();
            case CONFIRMATION_TRANSFER -> ConfirmationType.TRANSFER;
            case CONFIRMATION_TRANSPORT -> ConfirmationType.TRANSPORT;
        };
    }
}
