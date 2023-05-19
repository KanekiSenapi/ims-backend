package pl.aogiri.ims.report.common;

import com.google.cloud.storage.Blob;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.aogiri.ims.invoice.presentation.dto.invoicefile.InvoiceWithFile;
import pl.aogiri.ims.report.domain.value.Report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportUtils {

    public static String reportTitle(Report report) {
        String month = report.getYearMonth().toString();
        String owner = report.getOwner();

        return format("[%s] %s - Raport", month, owner);
    }

    public static String reportText(Report report) {
        return "text";
    }

    public static Map<String, Blob> reportFiles(Report report) {
        HashMap<String, Blob> nameWithContent = new HashMap<>();

        List<InvoiceWithFile> salesInvoices = report.getInvoices();

        salesInvoices.forEach(invoice -> {
            String invoiceType = invoice.getType().getFriendlyName();
            String invoiceNumber = invoice.getInvoiceNumber();
            invoice.getFiles().forEach(file -> {
                String fileExtension = file.getExtension();
                String fileType = file.getType().getFriendlyName();
                String fileName = format("%s_%s_%s.%s", invoiceType, invoiceNumber, fileType, fileExtension);
                nameWithContent.put(fileName, file.getFile());
            });
        });

        return nameWithContent;
    }

}
