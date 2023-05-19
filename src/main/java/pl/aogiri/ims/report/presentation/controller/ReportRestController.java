package pl.aogiri.ims.report.presentation.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.aogiri.ims.report.domain.service.ReportService;
import pl.aogiri.ims.report.domain.value.Report;

import java.time.Month;

@RequiredArgsConstructor
@RestController
public class ReportRestController implements ReportController {

    private final ReportService reportService;

    @Override
    public void sendReport(int year, int month) throws MessagingException {
        reportService.report(year, Month.of(month));
    }
}
