package dev.arena.book_network.dto.account;

import dev.arena.book_network.entities.Role;
import jakarta.validation.constraints.*;

public record AccountRequest(
        @NotNull
        @NotEmpty
        @NotBlank
        String firstName,
        @NotNull
        @NotEmpty
        @NotBlank
        String lastName,
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