package dev.arena.book_network.dto.feedback;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class FeedBackBookResponse  {
    private String title;
    private String authorName;
    private String synopsis;
}
