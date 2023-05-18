package pl.aogiri.ims.invoice.presentation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.aogiri.ims.common.dto.BaseFilterCriteria;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvoiceFilterCriteria extends BaseFilterCriteria {
    private String invoiceNumber;
    private String buyerName;
    private String sellerName;
}
