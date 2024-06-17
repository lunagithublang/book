package dev.arena.book_network.dto.feedback;
import jakarta.validation.constraints.*;
import java.util.UUID;

public record FeedBackRequest(
        @Min(value = 0)
        @Max(value = 5)
        @Positive
        Double note,

        @NotNull
        @NotEmpty
        @NotBlank
        String comment,

        @NotNull
        UUID bookId
) {
}
