package dev.arena.book_network.services.authentication;

import dev.arena.book_network.dto.authenticate.AuthenticateRequest;
import dev.arena.book_network.dto.authenticate.AuthenticateResponse;
import dev.arena.book_network.entities.Account;
import dev.arena.book_network.entities.Token;
import dev.arena.book_network.exceptions.InvalidTokenException;
import dev.arena.book_network.repositories.AccountRepository;
import dev.arena.book_network.repositories.TokenRepository;
import dev.arena.book_network.services.email.account.AccountEmailService;
import dev.arena.book_network.services.jwt.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Commented this out since I added keycloak
//@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService{

    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AccountEmailService accountEmailService;

    @Override
    public AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest) throws BadCredentialsException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.email(),
                        authenticateRequest.password()
                )
        );

        Account account  = (Account) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("fullName", account.getFullName());

        String jwtToken = jwtService.generateToken(claims, account);

        return new AuthenticateResponse(jwtToken, "");
    }

    @Override
//    @Transactional
    public void activateAccount(String token) throws MessagingException {
//        Token savedToken = tokenRepository.findByToken(token)
//                .orElseThrow(() -> new InvalidTokenException("Invalid token"));
//
//        // Check if the token is expired and not yet validated
//        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
//
//            if (savedToken.getAccount().isEnabled()) {
//                throw new RuntimeException("This Account is already activated");
//            }
//
//            if (savedToken.getValidatedAt() != null) {
//                throw new RuntimeException("This Account is already activated");
//            }
//
//            accountEmailService.sendValidationEmail(savedToken.getAccount());
//            throw new RuntimeException("Activation token has expired. New token has been sent");
//        }
//
//        // If the token is not expired but the account is already activated
//        if (savedToken.getAccount().isEnabled()) {
//            throw new RuntimeException("This Account is already activated");
//        }
//
//        Account account = savedToken.getAccount();
//        account.setEnabled(true);
//
//        accountRepository.save(account);
//
//        savedToken.setValidatedAt(LocalDateTime.now());
//        tokenRepository.save(savedToken);
    }
}
