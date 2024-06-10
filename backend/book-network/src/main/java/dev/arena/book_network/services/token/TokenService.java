package dev.arena.book_network.services.token;

import dev.arena.book_network.entities.Account;

public interface TokenService {
    String generateAndSaveActivationToken(Account account);
}
