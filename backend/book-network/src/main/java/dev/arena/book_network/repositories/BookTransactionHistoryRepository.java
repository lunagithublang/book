package dev.arena.book_network.repositories;

import dev.arena.book_network.entities.BookTransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, UUID> {
}
