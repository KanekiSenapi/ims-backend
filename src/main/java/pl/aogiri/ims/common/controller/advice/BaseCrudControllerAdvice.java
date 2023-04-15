package pl.aogiri.ims.common.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.aogiri.ims.common.dto.ErrorDTO;
import pl.aogiri.ims.common.exception.NotFoundException;

import java.time.Instant;

@ControllerAdvice
public class BaseCrudControllerAdvice {

    @ExceptionHandler(value = {NotFoundException.class})
    ErrorDTO handleNotFoundException(NotFoundException exception, WebRequest request) {
        return ErrorDTO.builder()
                .message(exception.getMessage())
                .path(request.getContextPath())
                .timestamp(Instant.now())
                .build();
    }
}
