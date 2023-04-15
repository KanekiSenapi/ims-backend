package pl.aogiri.ims.common.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.aogiri.ims.common.dto.ErrorDTO;
import pl.aogiri.ims.common.exception.NotFoundException;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BaseCrudControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorDTO handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        return ErrorDTO.builder()
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        final var errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ErrorDTO.builder()
                .message(errorMessage)
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .build();
    }
}
