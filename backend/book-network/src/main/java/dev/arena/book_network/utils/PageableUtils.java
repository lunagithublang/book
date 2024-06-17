package dev.arena.book_network.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {
    public static Pageable setPageable(int pageNumber, int pageSize, String sortedBy) {
        return PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(sortedBy).descending()
        );
    }
}
