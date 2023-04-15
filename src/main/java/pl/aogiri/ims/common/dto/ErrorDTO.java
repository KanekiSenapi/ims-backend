package pl.aogiri.ims.common.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ErrorDTO {
    private String message;
    private String path;
    private Instant timestamp;
}
