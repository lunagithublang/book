package dev.arena.book_network.services.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String generateTokenByUserDetails(UserDetails userDetails);
    Boolean isTokenValid(String token, UserDetails userDetails);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}
