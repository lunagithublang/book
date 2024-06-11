package dev.arena.book_network.repositories;

import dev.arena.book_network.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedBackRepository extends JpaRepository <Feedback, UUID> {
}
