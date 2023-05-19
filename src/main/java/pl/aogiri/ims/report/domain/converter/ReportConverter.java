package pl.aogiri.ims.report.domain.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.aogiri.ims.invoice.presentation.dto.invoicefile.InvoiceWithFile;
import pl.aogiri.ims.report.domain.value.Report;

import java.time.Month;
import java.time.YearMonth;
import java.util.List;

@Component
public class ReportConverter {

    @Value("${ims.owner}")
    private String owner;

    @Value("${ims.accounting.email}")
    private String accountingEmail;

    public Report of(YearMonth yearMonth, List<InvoiceWithFile> invoices) {
        return Report.builder()
                .owner(owner)
                .yearMonth(yearMonth)
                .targetEmail(accountingEmail)
                .invoices(invoices)
                .build();
    }
}
