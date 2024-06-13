package dev.arena.book_network.dto.book.history;

import dev.arena.book_network.dto.BaseResponse;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BookTransactionHistoryResponse extends BaseResponse  {
    private String title;
    private String authorName;
    private String isbn;
    private boolean isReturned;
    private boolean isApproved;
}
