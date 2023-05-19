package pl.aogiri.ims.invoice.presentation.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aogiri.ims.invoice.domain.entity.InvoiceType;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceUpsertRequest {

    private String file;
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