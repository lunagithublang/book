package dev.arena.book_network.services;

import dev.arena.book_network.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorService {
    public static ErrorResponse createErrorResponse(Map<String, String> errorMaps, HttpStatus status, HttpServletRequest request){
        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                request.getMethod(),
                errorMaps,
                request.getRequestURI()
        );
    }
}
