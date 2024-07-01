package dev.arena.book_network.controllers;

import dev.arena.book_network.dto.authenticate.AuthenticateRequest;
import dev.arena.book_network.dto.authenticate.AuthenticateResponse;
import dev.arena.book_network.services.authentication.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

// Commented this out since I added keycloak
//@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticateController {

    private final AuthenticationService authenticationService;

    @PostMapping("")
    public ResponseEntity<AuthenticateResponse> authenticate(@Valid @RequestBody AuthenticateRequest authenticateRequest) throws BadCredentialsException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authenticationService.authenticate(authenticateRequest));
    }

    @GetMapping("/activate-account")
    public void confirm(@RequestParam String token) throws MessagingException {
        authenticationService.activateAccount(token);
    }
}
