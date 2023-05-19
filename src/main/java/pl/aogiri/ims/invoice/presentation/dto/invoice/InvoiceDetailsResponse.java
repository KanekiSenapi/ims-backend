package pl.aogiri.ims.invoice.presentation.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aogiri.ims.customer.presentation.dto.CustomerDetailsResponse;
import pl.aogiri.ims.invoice.presentation.dto.invoiceitem.InvoiceItemDetailsResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsResponse {
    private UUID id;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate saleDate;
    private String issuePlace;
    private String paymentMethod;
    private CustomerDetailsResponse seller;
    private CustomerDetailsResponse buyer;
    private List<InvoiceItemDetailsResponse> items;
}
