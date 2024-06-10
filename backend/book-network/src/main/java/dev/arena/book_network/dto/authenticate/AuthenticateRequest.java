package dev.arena.book_network.dto.authenticate;

import jakarta.validation.constraints.*;

public record AuthenticateRequest(
        @NotNull
        @NotEmpty
        @NotBlank
        @Email
        String email,
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min= 8)
        String password
) {
}
