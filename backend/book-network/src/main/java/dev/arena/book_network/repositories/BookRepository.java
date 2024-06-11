package dev.arena.book_network.repositories;

import dev.arena.book_network.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository <Book, UUID> {
    Optional<Book> findById(UUID resourceId);
}
