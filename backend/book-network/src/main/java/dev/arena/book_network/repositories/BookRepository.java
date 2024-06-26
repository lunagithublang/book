package dev.arena.book_network.repositories;

import dev.arena.book_network.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository <Book, UUID> {

    @Query("""
         SELECT book
         FROM Book book
         WHERE book.id = :resourceId
         AND book.owner.id = :ownerId
         """
    )
    Optional<Book> findByIdAndOwnerId(UUID resourceId, UUID ownerId);

    Page<Book> findAllByOwnerId(UUID ownerId, Pageable pageable);

    @Query("""
         SELECT book
         FROM Book book
         WHERE book.isArchived = false
         AND book.isShareable = true
         AND book.owner.id != :ownerId
         """
    )
    Page<Book> findAllDisplayableBooks(UUID ownerId, Pageable pageable);

}
