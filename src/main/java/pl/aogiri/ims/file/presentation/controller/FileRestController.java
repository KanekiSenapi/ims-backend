package pl.aogiri.ims.file.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.aogiri.ims.file.domain.service.FileService;
import pl.aogiri.ims.file.presentation.dto.FileUploadRequest;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FileRestController implements FileController {

    private final FileService fileService;

    @Override
    public void uploadFileToGCS(MultipartFile file, FileUploadRequest request) throws IOException {
        fileService.handleRequest(file, request);
    }
}
