package dev.arena.book_network.services.account;

import dev.arena.book_network.dto.account.AccountRequest;
import dev.arena.book_network.dto.authenticate.AuthenticateResponse;
import dev.arena.book_network.entities.Account;
import dev.arena.book_network.entities.Role;
import dev.arena.book_network.exceptions.NotFoundEntityException;
import dev.arena.book_network.repositories.AccountRepository;
import dev.arena.book_network.repositories.RoleRepository;
import dev.arena.book_network.services.jwt.JwtService;
import dev.arena.book_network.services.email.account.AccountEmailService;
import dev.arena.book_network.services.token.TokenService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImplementation implements AccountService{

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final AccountEmailService accountEmailService;

    @Override
    @Transactional
    public AuthenticateResponse saveAccount(AccountRequest accountRequest) throws MessagingException {

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new NotFoundEntityException("Role not found!"));

        Account account = Account.builder()
                .firstName(accountRequest.firstName())
                .lastName(accountRequest.lastName())
                .email(accountRequest.email())
                .password(passwordEncoder.encode(accountRequest.password()))
                .isAccountLocked(false)
                .isEnabled(false)
                .roles(List.of(userRole))
                .build();

        accountRepository.save(account);
        accountEmailService.sendValidationEmail(account);

        return null;
    }
}
