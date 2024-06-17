package dev.arena.book_network.mappers;

import dev.arena.book_network.dto.book.BookResponse;
import dev.arena.book_network.dto.feedback.FeedBackBookResponse;
import dev.arena.book_network.dto.feedback.FeedBackRequest;
import dev.arena.book_network.dto.feedback.FeedBackResponse;
import dev.arena.book_network.entities.Book;
import dev.arena.book_network.entities.Feedback;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class FeedBackMapper {

    public Feedback toFeedBack(FeedBackRequest feedBackRequest) {

        Book book = Book.builder()
                .id(feedBackRequest.bookId())
                .build();

        return Feedback.builder()
                .note(feedBackRequest.note())
                .comment(feedBackRequest.comment())
                .book(book)
                .build();
    }


    public FeedBackResponse toResponse(Feedback feedback, UUID accountId) {

        FeedBackBookResponse feedBackBookResponse = FeedBackBookResponse.builder()
                .title(feedback.getBook().getTitle())
                .authorName(feedback.getBook().getAuthorName())
                .synopsis(feedback.getBook().getSynopsis())
                .build();

        return FeedBackResponse.builder()
                .id(feedback.getId())
                .note(feedback.getNote())
                .comment(feedback.getComment())
                .book(feedBackBookResponse)
                .ownFeedback(Objects.equals(feedback.getCreatedBy(), accountId))
                .createdBy(feedback.getCreatedBy())
                .createdAt(feedback.getCreatedAt())
                .updatedBy(feedback.getUpdatedBy())
                .updatedAt(feedback.getUpdatedAt())
                .build();
    }

}
