package pl.aogiri.ims.report.domain.value;

import lombok.*;
import pl.aogiri.ims.invoice.presentation.dto.invoicefile.InvoiceWithFile;

import java.time.YearMonth;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private String owner;

    private YearMonth yearMonth;

    private String targetEmail;

    private List<InvoiceWithFile> invoices;
}
