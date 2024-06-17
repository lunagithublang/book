package dev.arena.book_network.services.feedback;

import dev.arena.book_network.dto.PageResponse;
import dev.arena.book_network.dto.feedback.FeedBackRequest;
import dev.arena.book_network.dto.feedback.FeedBackResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

public interface FeedBackService {
    FeedBackResponse saveFeedBack(FeedBackRequest feedBackRequest, Authentication connectedUser);
    PageResponse<FeedBackResponse> findAllFeedBackByBookId(
            UUID bookId,
            int number,
            int size,
            UriComponentsBuilder uriComponentsBuilder,
            Authentication connectedUser
    );
}
