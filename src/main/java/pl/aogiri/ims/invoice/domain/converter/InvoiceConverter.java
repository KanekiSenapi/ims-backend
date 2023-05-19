package pl.aogiri.ims.invoice.domain.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.converter.BaseConverter;
import pl.aogiri.ims.customer.domain.converter.CustomerConverter;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.file.domain.value.File;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceBasicResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceDetailsResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceUpsertRequest;
import pl.aogiri.ims.invoice.presentation.dto.invoicefile.InvoiceWithFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InvoiceConverter extends BaseConverter<InvoiceEntity, InvoiceUpsertRequest, InvoiceDetailsResponse, InvoiceBasicResponse> {

    private final CustomerConverter customerConverter;
    private final InvoiceItemConverter invoiceItemConverter;

    @Override
    public InvoiceEntity toEntity(final InvoiceUpsertRequest request, final UUID id) {
        CustomerEntity buyerEntity = new CustomerEntity();
        buyerEntity.setId(request.getBuyerId());

        CustomerEntity sellerEntity = new CustomerEntity();
        sellerEntity.setId(request.getSellerId());

        return InvoiceEntity.builder()
                .id(id)
                .invoiceType(request.getInvoiceType())
                .invoiceNumber(request.getInvoiceNumber())
                .invoiceDate(request.getInvoiceDate())
                .saleDate(request.getSaleDate())
                .issuePlace(request.getIssuePlace())
                .paymentMethod(request.getPaymentMethod())
                .seller(sellerEntity)
                .buyer(buyerEntity)
                .additionalInfo(request.getAdditionalInfo())
                .file(request.getFile().toString())
                .build();
    }

    @Override
    public InvoiceDetailsResponse toDetailsResponse(final InvoiceEntity invoiceEntity) {
        return InvoiceDetailsResponse.builder()
                .id(invoiceEntity.getId())
                .invoiceNumber(invoiceEntity.getInvoiceNumber())
                .invoiceDate(invoiceEntity.getInvoiceDate())
                .saleDate(invoiceEntity.getSaleDate())
                .issuePlace(invoiceEntity.getIssuePlace())
                .paymentMethod(invoiceEntity.getPaymentMethod())
                .seller(customerConverter.toDetailsResponse(invoiceEntity.getSeller()))
                .buyer(customerConverter.toDetailsResponse(invoiceEntity.getBuyer()))
                .items(invoiceEntity.getItems().stream().map(invoiceItemConverter::toDetailsResponse).toList())
                .build();
    }

    @Override
    public InvoiceBasicResponse toBasicResponse(final InvoiceEntity invoiceEntity) {
        return InvoiceBasicResponse.builder()
                .id(invoiceEntity.getId())
                .invoiceNumber(invoiceEntity.getInvoiceNumber())
                .sellerName(invoiceEntity.getSeller().getName())
                .buyerName(invoiceEntity.getBuyer().getName())
                .totalGrossAmount(invoiceEntity.getTotalGrossAmount())
                .build();
    }

    public InvoiceWithFile toInvoiceWithFile(final InvoiceEntity invoiceEntity, final List<File> files) {
        return InvoiceWithFile.builder()
                .type(invoiceEntity.getInvoiceType())
                .invoiceNumber(invoiceEntity.getInvoiceNumber())
                .invoiceDate(invoiceEntity.getInvoiceDate())
                .files(files)
                .build();
    }
}
