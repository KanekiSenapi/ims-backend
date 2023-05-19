package pl.aogiri.ims.invoice.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aogiri.ims.file.domain.value.File;
import pl.aogiri.ims.file.presentation.dto.FileUploadRequest;
import pl.aogiri.ims.invoice.presentation.dto.invoice.InvoiceUpsertRequest;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class InvoiceParserService  {

    private final InvoiceService invoiceService;

    @Transactional
    public void parseInvoiceFile(File file, FileUploadRequest request) {
        if (request.getInvoiceId() == null) {

            InvoiceUpsertRequest invoiceUpsertRequest = new InvoiceUpsertRequest(
                    file.getFileUri().toString(),
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
        } else {
            invoiceService.update(request.getInvoiceId(), file.getFileUri().toString());
        }
    }
}
