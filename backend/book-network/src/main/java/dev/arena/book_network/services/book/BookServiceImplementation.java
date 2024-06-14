package dev.arena.book_network.services.book;

import dev.arena.book_network.constants.Constants;
import dev.arena.book_network.dto.PageResponse;
import dev.arena.book_network.dto.book.BookRequest;
import dev.arena.book_network.dto.book.BookResponse;
import dev.arena.book_network.dto.book.history.BookTransactionHistoryResponse;
import dev.arena.book_network.entities.Account;
import dev.arena.book_network.entities.Book;
import dev.arena.book_network.entities.BookTransactionHistory;
import dev.arena.book_network.exceptions.NotFoundEntityException;
import dev.arena.book_network.exceptions.OperationNotPermittedException;
import dev.arena.book_network.mappers.BookHistoryTransactionMapper;
import dev.arena.book_network.mappers.BookMapper2;
import dev.arena.book_network.repositories.BookRepository;
import dev.arena.book_network.repositories.BookTransactionHistoryRepository;
import dev.arena.book_network.services.file.FileService;
import dev.arena.book_network.utils.PageableUtils;
import dev.arena.book_network.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final BookTransactionHistoryRepository bookTransactionHistoryRepository;
    private final BookHistoryTransactionMapper bookHistoryTransactionMapper;
    private final BookMapper2 bookMapper2;
    private final FileService fileService;

    @Override
    @Transactional
    public BookResponse saveBook(BookRequest bookRequest, Authentication connectedUser) {
        Account account = (Account) connectedUser.getPrincipal();
        Book book = bookMapper2.toBook(bookRequest);
        book.setOwner(account);

        return bookMapper2.toResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse findById(UUID resourceId, Authentication connectedUser) {
        Account account = (Account) connectedUser.getPrincipal();
        Book book = bookRepository
                .findByIdAndOwnerId(resourceId, account.getId())
                .orElseThrow(() -> new NotFoundEntityException("Book not found!"));

        return bookMapper2.toResponse(book);
    }

    @Override
    @Transactional
    public BookResponse updateShareableStatusById(UUID resourceId, Authentication connectedUser) {
        Account account = (Account) connectedUser.getPrincipal();
        Book book = bookRepository
                .findByIdAndOwnerId(resourceId, account.getId())
                .orElseThrow(() -> new NotFoundEntityException("Book not found!"));
        book.setShareable(!book.isShareable());

        return bookMapper2.toResponse(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookResponse updateArchiveStatusById(UUID resourceId, Authentication connectedUser) {
        Account account = (Account) connectedUser.getPrincipal();
        Book book = bookRepository
                .findByIdAndOwnerId(resourceId, account.getId())
                .orElseThrow(() -> new NotFoundEntityException("Book not found!"));
        book.setArchived(!book.isArchived());

        return bookMapper2.toResponse(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookTransactionHistoryResponse borrowBook(UUID resourceId, Authentication connectedUser) {

        Book book = bookRepository
                .findById(resourceId)
                .orElseThrow(() -> new NotFoundEntityException("Book not found!"));

        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("This book cannot be borrowed since it is archived or not shareable");
        }

        Account account = (Account) connectedUser.getPrincipal();
        UUID accountId = account.getId();

        if (Objects.equals(book.getOwner().getId(), accountId)) {
            throw new OperationNotPermittedException("You cannot borrow your own book");
        }

        final boolean isAlreadyBorrowed = bookTransactionHistoryRepository.isBookAlreadyBorrowedByAccount(resourceId, accountId);
        if (isAlreadyBorrowed) {
            throw new OperationNotPermittedException("This book is already borrowed");
        }

        BookTransactionHistory bookTransactionHistory = BookTransactionHistory.builder()
                .account(account)
                .book(book)
                .isReturned(false)
                .isReturnedApproved(false)
                .build();

        return bookHistoryTransactionMapper.toResponse(
                bookTransactionHistoryRepository.save(bookTransactionHistory)
        );
    }

    @Override
    @Transactional
    public BookTransactionHistoryResponse returnBorrowedBook(UUID resourceId, Authentication connectedUser) {

        Book book = bookRepository
                .findById(resourceId)
                .orElseThrow(() -> new NotFoundEntityException("Book not found!"));

        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("This book cannot be borrowed since it is archived or not shareable");
        }

        Account account = (Account) connectedUser.getPrincipal();
        UUID accountId = account.getId();

        if (Objects.equals(book.getOwner().getId(), accountId)) {
            throw new OperationNotPermittedException("You cannot return your own book");
        }

        BookTransactionHistory bookTransactionHistory = bookTransactionHistoryRepository
                .findByBookIdAndAccountId(resourceId, accountId)
                .orElseThrow(() -> new OperationNotPermittedException("You did not borrow this book"));

        bookTransactionHistory.setReturned(true);

        bookTransactionHistoryRepository.save(bookTransactionHistory);

        return bookHistoryTransactionMapper.toResponse(bookTransactionHistory);
    }

    @Override
    @Transactional
    public BookTransactionHistoryResponse approveReturnBorrowedBook(UUID resourceId, Authentication connectedUser) {
        Book book = bookRepository
                .findById(resourceId)
                .orElseThrow(() -> new NotFoundEntityException("Book not found!"));

        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("This book cannot be borrowed since it is archived or not shareable");
        }

        Account account = (Account) connectedUser.getPrincipal();
        UUID accountId = account.getId();

//        if (Objects.equals(book.getOwner().getId(), accountId)) {
//            throw new OperationNotPermittedException("You cannot return your own book");
//        }

        BookTransactionHistory bookTransactionHistory = bookTransactionHistoryRepository
                .findByBookIdAndOwnerId(resourceId, accountId)
                .orElseThrow(() -> new OperationNotPermittedException("The book is not returned yet. You cannot not approve it."));

        bookTransactionHistory.setReturnedApproved(true);

        bookTransactionHistoryRepository.save(bookTransactionHistory);

        return bookHistoryTransactionMapper.toResponse(bookTransactionHistory);
    }

    @Override
    public void uploadBookCover(UUID resourceId, MultipartFile file, Authentication connectedUser) throws IOException {
        Book book = bookRepository
                .findById(resourceId)
                .orElseThrow(() -> new NotFoundEntityException("Book not found!"));

        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("This book cannot be borrowed since it is archived or not shareable");
        }

        Account account = (Account) connectedUser.getPrincipal();
        UUID accountId = account.getId();

        String bookCover = fileService.saveFile(accountId, file);
        book.setBookCover(bookCover);
        bookRepository.save(book);
    }

    @Override
    public PageResponse<BookResponse> findAllDisplayableBooks(
            int pageNumber,
            int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        uriComponentsBuilder.path("/books");
        Account account = (Account) connectedUser.getPrincipal();
        Pageable pageable = PageableUtils.setPageable(pageNumber, pageSize, Constants.CREATED_AT);
        Page<Book> bookPage = bookRepository.findAllDisplayableBooks(account.getId(), pageable);
        return PaginationUtils.createPageResponse(
                bookPage,
                bookMapper2::toResponse,
                uriComponentsBuilder
        );
    }

    @Override
    public PageResponse<BookResponse> findAllBooksByOwner(
            int pageNumber,
            int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        uriComponentsBuilder.path("/books");
        Account account = (Account) connectedUser.getPrincipal();
        Pageable pageable = PageableUtils.setPageable(pageNumber, pageSize, Constants.CREATED_AT);
        Page<Book> bookPage = bookRepository.findAllByOwnerId(account.getId(), pageable);
        return PaginationUtils.createPageResponse(
                bookPage,
                bookMapper2::toResponse,
                uriComponentsBuilder
        );
    }

    @Override
    public PageResponse<BookTransactionHistoryResponse> findAllBorrowedBooks(
            int pageNumber,
            int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        uriComponentsBuilder.path("/books");
        Account account = (Account) connectedUser.getPrincipal();
        Pageable pageable = PageableUtils.setPageable(pageNumber, pageSize, Constants.CREATED_AT);
        Page<BookTransactionHistory> bookTransactionHistoryPage = bookTransactionHistoryRepository.findAllBorrowedBooks(account.getId(), pageable);
        return PaginationUtils.createPageResponse(
                bookTransactionHistoryPage,
                bookHistoryTransactionMapper::toResponse,
                uriComponentsBuilder
        );
    }

    @Override
    public PageResponse<BookTransactionHistoryResponse> findAllReturnedBooks(
            int pageNumber,
            int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        uriComponentsBuilder.path("/books");
        Account account = (Account) connectedUser.getPrincipal();
        Pageable pageable = PageableUtils.setPageable(pageNumber, pageSize, Constants.CREATED_AT);
        Page<BookTransactionHistory> bookTransactionHistoryPage = bookTransactionHistoryRepository.findAllReturnedBooks(account.getId(), pageable);
        return PaginationUtils.createPageResponse(
                bookTransactionHistoryPage,
                bookHistoryTransactionMapper::toResponse,
                uriComponentsBuilder
        );
    }
}
