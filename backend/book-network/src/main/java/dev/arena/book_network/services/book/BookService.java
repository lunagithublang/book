package dev.arena.book_network.services.book;

import dev.arena.book_network.dto.PageResponse;
import dev.arena.book_network.dto.book.BookRequest;
import dev.arena.book_network.dto.book.BookResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

public interface BookService {
    BookResponse saveBook(BookRequest bookRequest, Authentication connectedUser);
    BookResponse findById(UUID resourceId);
    PageResponse<BookResponse> findAll(int pageNumber, int pageSize, UriComponentsBuilder uriComponentsBuilder);
}
