package dev.arena.book_network.dto.authenticate;

public record AuthenticateResponse(
        String accessToken,
        String refreshToken
) {
}
