package pl.aogiri.ims.invoice.presentation.dto.invoicefile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aogiri.ims.file.domain.value.File;
import pl.aogiri.ims.invoice.domain.entity.InvoiceType;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceWithFile {
    private LocalDate invoiceDate;
    private String invoiceNumber;
    private InvoiceType type;
    private List<File> files;

}
