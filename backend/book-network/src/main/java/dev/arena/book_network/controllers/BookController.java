package dev.arena.book_network.controllers;

import dev.arena.book_network.constants.Constants;
import dev.arena.book_network.dto.PageResponse;
import dev.arena.book_network.dto.book.BookRequest;
import dev.arena.book_network.dto.book.BookResponse;
import dev.arena.book_network.services.book.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping("")
    public ResponseEntity<PageResponse<BookResponse>> index(
            @RequestParam(name ="number", defaultValue = Constants.PAGE_NUMBER) int pageNumber,
            @RequestParam(name="size", defaultValue = Constants.PAGE_SIZE) int pageSize,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(bookService.findAll(pageNumber, pageSize, uriComponentsBuilder));
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<BookResponse> show(@PathVariable("resourceId") UUID resourceId) {
        return ResponseEntity.ok(bookService.findById(resourceId));
    }
}
