package dev.arena.book_network.services.authentication;

import dev.arena.book_network.dto.authenticate.AuthenticateRequest;
import dev.arena.book_network.dto.authenticate.AuthenticateResponse;
import jakarta.mail.MessagingException;
import org.springframework.security.authentication.BadCredentialsException;

public interface AuthenticationService {
    AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest) throws BadCredentialsException;
    void activateAccount(String token) throws MessagingException;
}
