package pl.aogiri.ims.common.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public class ErrorDTO {
    private String message;
    private String path;
    private Instant timestamp;
}
