package dev.arena.book_network.services.book;

import dev.arena.book_network.constants.Constants;
import dev.arena.book_network.dto.PageResponse;
import dev.arena.book_network.dto.book.BookRequest;
import dev.arena.book_network.dto.book.BookResponse;
import dev.arena.book_network.entities.Account;
import dev.arena.book_network.entities.Book;
import dev.arena.book_network.exceptions.NotFoundEntityException;
import dev.arena.book_network.mappers.BookMapper2;
import dev.arena.book_network.repositories.BookRepository;
import dev.arena.book_network.utils.PageableUtils;
import dev.arena.book_network.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper2 bookMapper2;

    @Override
    @Transactional
    public BookResponse saveBook(BookRequest bookRequest, Authentication connectedUser) {

        Account account = (Account) connectedUser.getPrincipal();
        Book book = bookMapper2.toBook(bookRequest);
        book.setOwner(account);

        return bookMapper2.toResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse findById(UUID resourceId) {
        Book book = bookRepository
                .findById(resourceId)
                .orElseThrow(() -> new NotFoundEntityException("Book not found!"));

        return bookMapper2.toResponse(book);
    }

    @Override
    public PageResponse<BookResponse> findAll(int pageNumber, int pageSize, UriComponentsBuilder uriComponentsBuilder) {

        uriComponentsBuilder.path("books");

        Pageable pageable = PageableUtils.setPageable(pageNumber, pageSize, Constants.CREATED_AT);

        Page bookPage = bookRepository.findAll(pageable);

        return PaginationUtils.createPageResponse(bookPage, bookMapper2::toResponse, uriComponentsBuilder);
    }
}
