package dev.arena.book_network.repositories;

import dev.arena.book_network.entities.BookTransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, UUID> {

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.account.id = :accountId
     """)
    Page<BookTransactionHistory> findAllBorrowedBooks(UUID accountId, Pageable pageable);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.book.owner.id = :ownerId
            AND history.isReturned = true
     """)
    Page<BookTransactionHistory> findAllReturnedBooks(UUID ownerId, Pageable pageable);

    @Query("""
            SELECT (COUNT(*) > 0) AS isBorrowed
            FROM BookTransactionHistory history
            WHERE history.account.id = :borrowerId
            AND history.book.id = :bookId
            AND history.isReturnedApproved = false
    """)
    boolean isBookAlreadyBorrowedByAccount(UUID bookId, UUID borrowerId);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.account.id = :accountId
            AND history.book.id = :bookId
            AND history.isReturned = false
            AND history.isReturnedApproved = false
     """)
    Optional<BookTransactionHistory> findByBookIdAndAccountId(UUID bookId, UUID accountId);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.book.owner.id = :ownerId
            AND history.book.id = :bookId
            AND history.isReturned = true
            AND history.isReturnedApproved = false
     """)
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(UUID bookId, UUID ownerId);
}
