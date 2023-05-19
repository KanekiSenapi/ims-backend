package pl.aogiri.ims.invoice.presentation.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aogiri.ims.invoice.domain.entity.InvoiceType;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceBasicResponse {
    private UUID id;
    private String invoiceNumber;
    private InvoiceType type;
    private String sellerName;
    private String buyerName;
    private BigDecimal totalGrossAmount;
    private boolean haveFile;
}
