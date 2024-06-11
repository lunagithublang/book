package dev.arena.book_network.dto.book;

import dev.arena.book_network.dto.BaseResponse;
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
public class BookResponse extends BaseResponse {
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean isArchived;
    private boolean isShareable;
    private UUID createdBy;
    private UUID updatedBy;
    private String owner;
    private byte[] cover;
    private double rate;
}
