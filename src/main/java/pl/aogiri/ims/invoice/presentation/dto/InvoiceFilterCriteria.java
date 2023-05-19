package pl.aogiri.ims.invoice.presentation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.aogiri.ims.common.dto.BaseFilterCriteria;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvoiceFilterCriteria extends BaseFilterCriteria {
    private String invoiceNumber;
    private String buyerName;
    private String sellerName;
    private LocalDate from;
    private LocalDate to;
}
