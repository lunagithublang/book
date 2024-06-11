package dev.arena.book_network.dto.book;

import dev.arena.book_network.dto.BaseResponse;
import java.util.UUID;

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
}
