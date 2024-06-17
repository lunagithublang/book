package dev.arena.book_network.services.book;

import dev.arena.book_network.dto.PageResponse;
import dev.arena.book_network.dto.book.BookRequest;
import dev.arena.book_network.dto.book.BookResponse;
import dev.arena.book_network.dto.book.history.BookTransactionHistoryResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.UUID;

public interface BookService {
    BookResponse saveBook(BookRequest bookRequest, Authentication connectedUser);
    BookResponse findById(UUID resourceId, Authentication connectedUser);
    BookResponse updateShareableStatusById(UUID resourceId, Authentication connectedUser);
    BookResponse updateArchiveStatusById(UUID resourceId, Authentication connectedUser);
    BookTransactionHistoryResponse borrowBook(UUID resourceId, Authentication connectedUser);
    BookTransactionHistoryResponse returnBorrowedBook(UUID resourceId, Authentication connectedUser);
    BookTransactionHistoryResponse approveReturnBorrowedBook(UUID resourceId, Authentication connectedUser);
    void uploadBookCover(UUID resourceId, MultipartFile file, Authentication connectedUser) throws IOException;

    PageResponse<BookResponse> findAllDisplayableBooks(
            int pageNumber,
            int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    );

    PageResponse<BookResponse> findAllBooksByOwner(
            int pageNumber,
            int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    );

    PageResponse<BookTransactionHistoryResponse> findAllBorrowedBooks(
            int pageNumber,
            int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    );

    PageResponse<BookTransactionHistoryResponse> findAllReturnedBooks(
            int pageNumber,
            int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    );
}
