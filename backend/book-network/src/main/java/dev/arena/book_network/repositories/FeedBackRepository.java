package dev.arena.book_network.repositories;

import dev.arena.book_network.entities.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FeedBackRepository extends JpaRepository <Feedback, UUID> {

    @Query("""
            SELECT fb
            FROM Feedback fb
            WHERE fb.book.id = :bookId
     """)
    Page<Feedback> findAllFeedBackByBookId(UUID bookId, Pageable pageable);
}
