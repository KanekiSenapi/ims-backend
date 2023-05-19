package pl.aogiri.ims.file.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aogiri.ims.file.domain.value.FileType;
import pl.aogiri.ims.invoice.domain.entity.InvoiceType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadRequest implements Serializable {
    private FileType fileType;
    private UUID invoiceId;

    private InvoiceType invoiceType;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate saleDate;
    private String issuePlace;
    private String paymentMethod;
    private UUID sellerId;
    private UUID buyerId;
    private String additionalInfo;

}
