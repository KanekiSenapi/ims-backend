package pl.aogiri.ims.file.presentation.dto;

import pl.aogiri.ims.file.domain.value.FileType;

public record FileBasicResponse(String filename, FileType fileType) {
}
