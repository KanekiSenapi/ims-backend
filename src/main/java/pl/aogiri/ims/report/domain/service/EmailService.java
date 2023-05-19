package pl.aogiri.ims.report.domain.service;

import com.google.cloud.storage.Blob;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.aogiri.ims.report.common.ReportUtils;
import pl.aogiri.ims.report.domain.value.Report;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendReport(Report report) throws MessagingException {
        String targetEmail = report.getTargetEmail();
        String title = ReportUtils.reportTitle(report);
        String text = ReportUtils.reportText(report);
        Map<String, Blob> files = ReportUtils.reportFiles(report);


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(targetEmail);
        helper.setSubject(title);
        helper.setText(text);

        for (Map.Entry<String, Blob> entry : files.entrySet()) {
            String fileName = entry.getKey();
            Blob blob = entry.getValue();
            byte[] blobData = blob.getContent();
            ByteArrayDataSource dataSource = new ByteArrayDataSource(blobData, "application/octet-stream");
            helper.addAttachment(fileName, dataSource);
        }

        javaMailSender.send(message);
    }
}
