package dev.arena.book_network.services.account;

import dev.arena.book_network.dto.account.AccountRequest;
import dev.arena.book_network.dto.authenticate.AuthenticateResponse;
import jakarta.mail.MessagingException;

public interface AccountService {
    AuthenticateResponse saveAccount(AccountRequest accountRequest) throws MessagingException;
}
