package pl.aogiri.ims.report.domain.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.aogiri.ims.confirmation.domain.entity.ConfirmationEntity;
import pl.aogiri.ims.confirmation.domain.entity.ConfirmationType;
import pl.aogiri.ims.file.domain.service.FileService;
import pl.aogiri.ims.file.domain.value.File;
import pl.aogiri.ims.file.domain.value.FileType;
import pl.aogiri.ims.invoice.domain.converter.InvoiceConverter;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;
import pl.aogiri.ims.invoice.domain.service.InvoiceService;
import pl.aogiri.ims.invoice.presentation.dto.InvoiceFilterCriteria;
import pl.aogiri.ims.invoice.presentation.dto.invoicefile.InvoiceWithFile;
import pl.aogiri.ims.report.domain.converter.ReportConverter;
import pl.aogiri.ims.report.domain.value.Report;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final InvoiceService invoiceService;
    private final FileService fileService;
    private final EmailService emailService;
    private final InvoiceConverter invoiceConverter;
    private final ReportConverter reportConverter;

    public Report report(int year, Month month) throws MessagingException {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate from = yearMonth.atDay(1);
        LocalDate to = yearMonth.atEndOfMonth();
        InvoiceFilterCriteria invoiceFilterCriteria = new InvoiceFilterCriteria();
        invoiceFilterCriteria.setFrom(from);
        invoiceFilterCriteria.setFrom(to);

        List<InvoiceEntity> invoicesInGivenMonth = invoiceService.findRaw(invoiceFilterCriteria);
        List<InvoiceWithFile> invoiceWithFileStream = invoicesInGivenMonth.stream().map(invoice -> {
                    List<ConfirmationEntity> confirmations = invoice.getConfirmations();
                    List<File> files = confirmations.stream()
                            .map(confirmation -> fileService.pullFileFromGCS(confirmation.getFile(), mapToFileType(confirmation.getType())))
                            .collect(Collectors.toList());
                    if (invoice.getFile() != null) {
                        File file = fileService.pullFileFromGCS(invoice.getFile(), FileType.INVOICE);
                        files.add(file);
                    }
                    return invoiceConverter.toInvoiceWithFile(invoice, files);
                })
                .toList();

        Report report = reportConverter.of(yearMonth, invoiceWithFileStream);

        emailService.sendReport(report);
        return report;
    }

    private FileType mapToFileType(ConfirmationType fileType) {
        return switch (fileType) {
            case TRANSFER -> FileType.CONFIRMATION_TRANSFER;
            case TRANSPORT -> FileType.CONFIRMATION_TRANSPORT;
        };
    }
}
