package dev.arena.book_network.services.token;

import dev.arena.book_network.entities.Account;
import dev.arena.book_network.entities.Token;
import dev.arena.book_network.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenServiceImplementation implements TokenService{

//    private final TokenRepository tokenRepository;

    @Override
    public String generateAndSaveActivationToken(Account account) {

//        String generatedToken = generateActivationCode();
//
//        Token token = Token.builder()
//                .token(generatedToken)
//                .expiresAt(LocalDateTime.now().plusMinutes(15))
//                .accountId(account.getId())
////                .account(account)
//                .build();
//
//        tokenRepository.save(token);
//
//        return generatedToken;

        return null;
    }

    private String generateActivationCode() {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }
}
