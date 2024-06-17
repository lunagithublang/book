package dev.arena.book_network.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public record BookRequest(
        @NotBlank
        @NotEmpty
        String title,
        @NotBlank
        @NotEmpty
        String authorName,
        @NotBlank
        @NotEmpty
        String isbn,
        @NotBlank
        @NotEmpty
        String synopsis,
        boolean isShareable
) {
}
