package pl.aogiri.ims.invoice.presentation.dto.invoiceitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemDetailsResponse {
    private UUID id;
    private String name;
    private Integer quantity;
    private String unit;
    private Integer tax;
    private BigDecimal totalNetAmount;
    private BigDecimal totalGrossAmount;
}
