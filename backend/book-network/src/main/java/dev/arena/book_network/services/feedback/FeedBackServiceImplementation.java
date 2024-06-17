package dev.arena.book_network.services.feedback;

import dev.arena.book_network.constants.Constants;
import dev.arena.book_network.dto.PageResponse;
import dev.arena.book_network.dto.feedback.FeedBackRequest;
import dev.arena.book_network.dto.feedback.FeedBackResponse;
import dev.arena.book_network.entities.Account;
import dev.arena.book_network.entities.Book;
import dev.arena.book_network.entities.Feedback;
import dev.arena.book_network.exceptions.NotFoundEntityException;
import dev.arena.book_network.exceptions.OperationNotPermittedException;
import dev.arena.book_network.mappers.FeedBackMapper;
import dev.arena.book_network.repositories.BookRepository;
import dev.arena.book_network.repositories.FeedBackRepository;
import dev.arena.book_network.utils.PageableUtils;
import dev.arena.book_network.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedBackServiceImplementation implements FeedBackService{

    private final FeedBackRepository feedBackRepository;
    private final BookRepository bookRepository;
    private final FeedBackMapper feedBackMapper;

    @Override
    @Transactional
    public FeedBackResponse saveFeedBack(FeedBackRequest feedBackRequest, Authentication connectedUser) {
        System.out.println("feedBackRequest.bookId() " + feedBackRequest.bookId());
        Book book = bookRepository
                .findById(feedBackRequest.bookId())
                .orElseThrow(() -> new NotFoundEntityException("Book not found!"));

        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give a feed for an archived or not shareable book");
        }

        Account account = (Account) connectedUser.getPrincipal();
        UUID accountId = account.getId();

        if (Objects.equals(book.getOwner().getId(), accountId)) {
            throw new OperationNotPermittedException("You cannot give a feedback to your own book");
        }

        Feedback feedback = feedBackMapper.toFeedBack(feedBackRequest);
        feedBackRepository.save(feedback);

        return feedBackMapper.toResponse(feedback, accountId);
    }

    @Override
    public PageResponse<FeedBackResponse> findAllFeedBackByBookId(
            UUID bookId,
            int number,
            int size,
            UriComponentsBuilder uriComponentsBuilder,
            Authentication connectedUser
    ) {
        uriComponentsBuilder.path("/feedbacks");

        Account account = (Account) connectedUser.getPrincipal();
        UUID accountId = account.getId();
        Pageable pageable = PageableUtils.setPageable(number, size, Constants.CREATED_AT);
        Page<Feedback> feedbackPage = feedBackRepository.findAllFeedBackByBookId(bookId, pageable);

        return PaginationUtils.createPageResponse(
                feedbackPage,
                feedback -> feedBackMapper.toResponse(feedback, accountId),
                uriComponentsBuilder
        );
    }
}
