package pl.aogiri.ims.report.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.aogiri.ims.report.domain.value.Report;

@Tag(name = "Report Controller", description = "Operations related to reports")
@RequestMapping("/report")
public interface ReportController {

    @PostMapping(value = "/send")
    void sendReport(@RequestParam int year, @RequestParam int month) throws MessagingException;

}