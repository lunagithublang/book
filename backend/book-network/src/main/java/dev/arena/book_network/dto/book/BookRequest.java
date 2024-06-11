package dev.arena.book_network.dto.book;

import jakarta.persistence.Column;

import java.util.UUID;

public record BookRequest(
    String title,
    String authorName,
    String isbn,
    String synopsis,
    String bookCover,
    boolean isArchived,
    boolean isShareable,
    UUID createdBy,
    UUID updatedBy
) {
}
