package pl.aogiri.ims.invoice.domain.converter;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.aogiri.ims.common.converter.BaseConverter;
import pl.aogiri.ims.confirmation.domain.entity.ConfirmationEntity;
import pl.aogiri.ims.customer.domain.converter.CustomerConverter;
import pl.aogiri.ims.customer.domain.entity.CustomerEntity;
import pl.aogiri.ims.file.domain.value.File;
import pl.aogiri.ims.file.domain.value.FileType;
import pl.aogiri.ims.file.presentation.dto.FileBasicResponse;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceBasicResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceDetailsResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceUpsertRequest;
import pl.aogiri.ims.invoice.presentation.dto.invoicefile.InvoiceWithFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static pl.aogiri.ims.report.domain.service.ReportService.mapToFileType;

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
                .file(request.getFile())
                .build();
    }

    @Override
    public InvoiceDetailsResponse toDetailsResponse(final InvoiceEntity invoiceEntity) {
        return InvoiceDetailsResponse.builder()
                .id(invoiceEntity.getId())
                .invoiceNumber(invoiceEntity.getInvoiceNumber())
                .invoiceDate(invoiceEntity.getInvoiceDate())
                .invoiceType(invoiceEntity.getInvoiceType())
                .saleDate(invoiceEntity.getSaleDate())
                .issuePlace(invoiceEntity.getIssuePlace())
                .paymentMethod(invoiceEntity.getPaymentMethod())
                .seller(customerConverter.toDetailsResponse(invoiceEntity.getSeller()))
                .buyer(customerConverter.toDetailsResponse(invoiceEntity.getBuyer()))
                .items(invoiceEntity.getItems().stream().map(invoiceItemConverter::toDetailsResponse).toList())
                .files(getFiles(invoiceEntity))
                .build();


    }

    @Override
    public InvoiceBasicResponse toBasicResponse(final InvoiceEntity invoiceEntity) {
        return InvoiceBasicResponse.builder()
                .id(invoiceEntity.getId())
                .invoiceNumber(invoiceEntity.getInvoiceNumber())
                .type(invoiceEntity.getInvoiceType())
                .sellerName(invoiceEntity.getSeller().getName())
                .buyerName(invoiceEntity.getBuyer().getName())
                .totalGrossAmount(invoiceEntity.getTotalGrossAmount())
                .haveFile(invoiceEntity.getFile() != null)
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

    private List<FileBasicResponse> getFiles(InvoiceEntity invoice) {
        List<ConfirmationEntity> confirmations = ObjectUtils.firstNonNull(invoice.getConfirmations(), List.of());

        List<FileBasicResponse> files = confirmations.stream()
                .map(confirmation -> {
                    FileType fileType = mapToFileType(confirmation.getType());
                    String filename = extractFilename(confirmation.getFile().getPath());
                    return new FileBasicResponse(filename, fileType);
                }).collect(Collectors.toList());

        if (invoice.getFile() != null) {
            String filename = extractFilename(invoice.getFile().getPath());
            files.add(new FileBasicResponse(filename, FileType.INVOICE));
        }

        return files;
    }

    private String extractFilename(String path) {
        String filename = StringUtils.substringAfterLast(path, "/");
        return StringUtils.substringBeforeLast(filename, ".");
    }
}
