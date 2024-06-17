package dev.arena.book_network.dto.book.history;

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
public class BookTransactionHistoryResponse extends BaseResponse  {
    private UUID bookId;
    private String title;
    private String authorName;
    private String isbn;
    private double rate;
    private boolean isReturned;
    private boolean isApproved;
}
