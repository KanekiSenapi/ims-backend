package pl.aogiri.ims.confirmation.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aogiri.ims.file.domain.value.FileType;

import java.net.URI;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationUpsertRequest {
    private UUID invoiceId;
    private URI fileLocation;
    private FileType type;
}
