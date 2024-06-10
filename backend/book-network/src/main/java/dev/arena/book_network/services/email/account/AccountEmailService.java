package dev.arena.book_network.services.email.account;

import dev.arena.book_network.entities.Account;
import jakarta.mail.MessagingException;

public interface AccountEmailService {
    void sendValidationEmail(Account account) throws MessagingException;
}
