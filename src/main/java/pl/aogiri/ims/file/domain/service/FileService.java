package pl.aogiri.ims.file.domain.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.aogiri.ims.common.provider.GCSProvider;
import pl.aogiri.ims.confirmation.domain.converter.ConfirmationConverter;
import pl.aogiri.ims.confirmation.domain.entity.ConfirmationEntity;
import pl.aogiri.ims.confirmation.domain.service.ConfirmationService;
import pl.aogiri.ims.confirmation.presentation.dto.ConfirmationUpsertRequest;
import pl.aogiri.ims.file.domain.value.File;
import pl.aogiri.ims.file.domain.value.FileType;
import pl.aogiri.ims.file.presentation.dto.FileUploadRequest;
import pl.aogiri.ims.invoice.domain.entity.InvoiceEntity;
import pl.aogiri.ims.invoice.domain.repository.InvoiceRepository;
import pl.aogiri.ims.invoice.domain.service.InvoiceParserService;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${ims.gcp.storage.bucket}")
    private String bucket;

    private final GCSProvider gcsProvider;
    private final InvoiceParserService invoiceService;
    private final InvoiceRepository invoiceRepository;
    private final ConfirmationService confirmationService;
    private final ConfirmationConverter confirmationConverter;

    public File pullFileFromGCS(URI uri, FileType fileType) {
        Storage storage = gcsProvider.get();

        BlobId blobId = BlobId.fromGsUtilUri(uri.toString());
        Blob blob = storage.get(blobId);
        String filename = StringUtils.substringBeforeLast(blob.getName(), ".");
        String extension = FilenameUtils.getExtension(filename);

        return new File(extension, blob, fileType);
    }

    public void handleRequest(MultipartFile file, FileUploadRequest request) throws IOException {
        switch (request.getFileType()) {
            case INVOICE -> handleNewInvoice(file, request);
            case CONFIRMATION_TRANSFER, CONFIRMATION_TRANSPORT -> handleConfirmation(file, request);
        }
    }

    private void handleConfirmation(MultipartFile file, FileUploadRequest request) throws IOException {
        FileType fileType = request.getFileType();
        File invoiceFile = pushFileIntoGCS(file, fileType);
        URI uri = invoiceFile.getFileUri();
        InvoiceEntity invoiceEntity = invoiceRepository.findById(request.getInvoiceId()).orElseThrow();
        ConfirmationEntity entity = confirmationConverter.toEntity(new ConfirmationUpsertRequest(request.getInvoiceId(), uri, fileType));
        invoiceEntity.getConfirmations().add(entity);
        invoiceRepository.save(invoiceEntity);
    }

    private void handleNewInvoice(MultipartFile file, FileUploadRequest request) throws IOException {
        File invoiceFile = pushFileIntoGCS(file, FileType.INVOICE);
        invoiceService.parseInvoiceFile(invoiceFile, request);
    }

    public File pushFileIntoGCS(MultipartFile file, FileType fileType) throws IOException {
        Storage storage = gcsProvider.get();

        String filename = StringUtils.replace(file.getOriginalFilename()," ", "_");
        String extension = FilenameUtils.getExtension(filename);

        String gcpFilename = String.format("%s.%s", filename,  UUID.randomUUID());

        BlobId blobId = BlobId.of(bucket, gcpFilename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        Blob blob = storage.createFrom(blobInfo, file.getInputStream());

        return new File(extension, blob, fileType);
    }
}
