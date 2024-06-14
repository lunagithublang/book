package dev.arena.book_network.controllers;

import dev.arena.book_network.constants.Constants;
import dev.arena.book_network.dto.PageResponse;
import dev.arena.book_network.dto.feedback.FeedBackRequest;
import dev.arena.book_network.dto.feedback.FeedBackResponse;
import dev.arena.book_network.services.feedback.FeedBackService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("feedbacks")
@Tag(name= "Feedback")
public class FeedBackController {

    private final FeedBackService feedBackService;

    @PostMapping("")
    public ResponseEntity<FeedBackResponse> createFeedBack(
            @Valid @RequestBody FeedBackRequest feedBackRequest,
            Authentication connectedUser
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feedBackService.saveFeedBack(feedBackRequest, connectedUser));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<PageResponse<FeedBackResponse>> index(
            @PathVariable("bookId")UUID bookId,
            @RequestParam(name="number", defaultValue = Constants.PAGE_NUMBER) int number,
            @RequestParam(name="size", defaultValue = Constants.PAGE_SIZE) int size,
            UriComponentsBuilder uriComponentsBuilder,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(
                feedBackService.findAllFeedBackByBookId(
                        bookId,
                        number,
                        size,
                        uriComponentsBuilder,
                        connectedUser
                )
        );
    }
}
