package pl.aogiri.ims.invoice.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.aogiri.ims.file.domain.value.File;
import pl.aogiri.ims.file.presentation.dto.FileUploadRequest;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceUpsertRequest;

@RequiredArgsConstructor
@Service
public class InvoiceParserService  {

    private final InvoiceService invoiceService;

    public void parseInvoiceFile(File file, FileUploadRequest request) {
        InvoiceUpsertRequest invoiceUpsertRequest = new InvoiceUpsertRequest(
                file.getFileUri(),
                request.getInvoiceType(),
                request.getInvoiceNumber(),
                request.getInvoiceDate(),
                request.getSaleDate(),
                request.getIssuePlace(),
                request.getPaymentMethod(),
                request.getSellerId(),
                request.getBuyerId(),
                request.getAdditionalInfo()
        );
        invoiceService.create(invoiceUpsertRequest);
    }
}
