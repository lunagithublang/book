package dev.arena.book_network.dto.feedback;

import dev.arena.book_network.dto.BaseResponse;
import dev.arena.book_network.dto.book.BookResponse;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class FeedBackResponse extends BaseResponse {
    private Double note;
    private String comment;
    private boolean ownFeedback;
    private UUID createdBy;
    private UUID updatedBy;
    private FeedBackBookResponse book;
}
