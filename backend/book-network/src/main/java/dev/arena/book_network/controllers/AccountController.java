package dev.arena.book_network.controllers;

import dev.arena.book_network.dto.account.AccountRequest;
import dev.arena.book_network.dto.authenticate.AuthenticateResponse;
import dev.arena.book_network.services.account.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
@Tag(name = "Account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticateResponse> create(@Valid @RequestBody AccountRequest accountRequest) throws MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.saveAccount(accountRequest));
    }
}
