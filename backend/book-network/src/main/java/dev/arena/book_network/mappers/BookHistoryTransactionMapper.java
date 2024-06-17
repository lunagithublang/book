package dev.arena.book_network.mappers;

import dev.arena.book_network.dto.book.history.BookTransactionHistoryResponse;
import dev.arena.book_network.entities.BookTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class BookHistoryTransactionMapper {

    public BookTransactionHistoryResponse toResponse(BookTransactionHistory bookTransactionHistory) {
        return BookTransactionHistoryResponse.builder()
                .id(bookTransactionHistory.getId())
                .bookId(bookTransactionHistory.getBook().getId())
                .authorName(bookTransactionHistory.getBook().getAuthorName())
                .isbn(bookTransactionHistory.getBook().getIsbn())
                .title(bookTransactionHistory.getBook().getTitle())
                .isReturned(bookTransactionHistory.isReturned())
                .isApproved(bookTransactionHistory.isReturnedApproved())
                .rate(bookTransactionHistory.getBook().getRate())
                .createdAt(bookTransactionHistory.getCreatedAt())
                .updatedAt(bookTransactionHistory.getUpdatedAt())
                .build();
    }
}
