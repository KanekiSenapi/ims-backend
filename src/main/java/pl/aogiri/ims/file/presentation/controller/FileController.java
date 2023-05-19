package pl.aogiri.ims.file.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.aogiri.ims.file.domain.value.File;
import pl.aogiri.ims.file.presentation.dto.FileUploadRequest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Tag(name = "File Controller", description = "Operations related to files")
@RequestMapping("/file")
public interface FileController {

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    void uploadFileToGCS(@RequestPart("file") MultipartFile file, @RequestPart FileUploadRequest request) throws IOException;

}
