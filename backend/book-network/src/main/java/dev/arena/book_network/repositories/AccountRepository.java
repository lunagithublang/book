package dev.arena.book_network.repositories;

import dev.arena.book_network.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository { // extends JpaRepository<Account, UUID> {
//    Optional<Account> findByEmail(String accountEmail);
}
