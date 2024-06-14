package dev.arena.book_network.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> data,
        int pageNumber,
        int pageSize,
        int totalPages,
        String nextPageUrl,
        String previousUrl
) {
}
