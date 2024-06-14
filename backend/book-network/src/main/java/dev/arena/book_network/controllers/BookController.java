package dev.arena.book_network.controllers;

import dev.arena.book_network.constants.Constants;
import dev.arena.book_network.dto.PageResponse;
import dev.arena.book_network.dto.book.BookRequest;
import dev.arena.book_network.dto.book.BookResponse;
import dev.arena.book_network.dto.book.history.BookTransactionHistoryResponse;
import dev.arena.book_network.services.book.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("books")
@Tag(name = "Book")
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<BookResponse> create(
            @Valid @RequestBody BookRequest bookRequest,
            Authentication connectedUser
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.saveBook(bookRequest, connectedUser));
    }

    @PostMapping("/borrow/{resourceId}")
    public ResponseEntity<BookTransactionHistoryResponse> borrowBook(
            @PathVariable("resourceId") UUID resourceId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(
                bookService.borrowBook(resourceId, connectedUser)
        );
    }

    @PatchMapping("/borrow/return/{resourceId}")
    public ResponseEntity<BookTransactionHistoryResponse> returnBorrowedBook(
            @PathVariable("resourceId") UUID resourceId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(
                bookService.returnBorrowedBook(resourceId, connectedUser)
        );
    }

    @PatchMapping("/borrow/return/approved/{resourceId}")
    public ResponseEntity<BookTransactionHistoryResponse> approveReturnBorrowedBook(
            @PathVariable("resourceId") UUID resourceId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(
                bookService.approveReturnBorrowedBook(resourceId, connectedUser)
        );
    }

    @GetMapping("")
    public ResponseEntity<PageResponse<BookResponse>> displayAllBooks(
            @RequestParam(name ="number", defaultValue = Constants.PAGE_NUMBER) int pageNumber,
            @RequestParam(name="size", defaultValue = Constants.PAGE_SIZE) int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(
                bookService.findAllDisplayableBooks(pageNumber, pageSize, connectedUser, uriComponentsBuilder)
        );
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
            @RequestParam(name ="number", defaultValue = Constants.PAGE_NUMBER) int pageNumber,
            @RequestParam(name="size", defaultValue = Constants.PAGE_SIZE) int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(
                bookService.findAllBooksByOwner(pageNumber, pageSize, connectedUser, uriComponentsBuilder)
        );
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BookTransactionHistoryResponse>> findAllBorrowedBooks(
            @RequestParam(name ="number", defaultValue = Constants.PAGE_NUMBER) int pageNumber,
            @RequestParam(name="size", defaultValue = Constants.PAGE_SIZE) int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(
                bookService.findAllBorrowedBooks(pageNumber, pageSize, connectedUser, uriComponentsBuilder)
        );
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BookTransactionHistoryResponse>> findAllReturnedBooks(
            @RequestParam(name ="number", defaultValue = Constants.PAGE_NUMBER) int pageNumber,
            @RequestParam(name="size", defaultValue = Constants.PAGE_SIZE) int pageSize,
            Authentication connectedUser,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(
                bookService.findAllReturnedBooks(pageNumber, pageSize, connectedUser, uriComponentsBuilder)
        );
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<BookResponse> show(
            @PathVariable("resourceId") UUID resourceId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findById(resourceId, connectedUser));
    }

    @PatchMapping("/shareable/{resourceId}")
    public ResponseEntity<BookResponse> updateShareableStatus(
            @PathVariable("resourceId") UUID resourceId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.updateShareableStatusById(resourceId, connectedUser));
    }

    @PatchMapping("/archive/{resourceId}")
    public ResponseEntity<BookResponse> updateArchiveStatus(
            @PathVariable("resourceId") UUID resourceId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.updateArchiveStatusById(resourceId, connectedUser));
    }

    @PostMapping(value = "/cover/{resourceId}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadBookCover(
            @PathVariable("resourceId") UUID resourceId,
            @Parameter()
            @RequestPart("file") MultipartFile file,
            Authentication connectedUser
    ) throws IOException {
        bookService.uploadBookCover(resourceId, file, connectedUser);
        return ResponseEntity.accepted().build();
    }
}
