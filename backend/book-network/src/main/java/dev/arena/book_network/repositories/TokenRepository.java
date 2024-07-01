package dev.arena.book_network.repositories;

import dev.arena.book_network.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository { //extends JpaRepository<Token, UUID> {
//    Optional<Token> findByToken(String token);
}
