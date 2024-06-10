package dev.arena.book_network.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String method,
        Map<String, String> errors,
        String path
) {
}
