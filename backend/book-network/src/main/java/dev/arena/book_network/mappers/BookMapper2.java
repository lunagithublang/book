package dev.arena.book_network.mappers;


import dev.arena.book_network.dto.book.BookRequest;
import dev.arena.book_network.dto.book.BookResponse;
import dev.arena.book_network.entities.Book;
import org.springframework.stereotype.Service;
import dev.arena.book_network.utils.FileUtils;

@Service
public class BookMapper2 {

    public Book toBook(BookRequest bookRequest) {
        return Book.builder()
                .title(bookRequest.title())
                .authorName(bookRequest.authorName())
                .isbn(bookRequest.isbn())
                .synopsis(bookRequest.synopsis())
                .isShareable(bookRequest.isShareable())
                .isArchived(false)
                .build();
    }

    public BookResponse toResponse(Book book) {
        System.out.println("book.getBookCover() " + book.getBookCover());
        return BookResponse.builder()
                .id(book.getId())
                .authorName(book.getAuthorName())
                .bookCover(book.getBookCover())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .synopsis(book.getSynopsis())
                .isArchived(book.isArchived())
                .isShareable(book.isShareable())
                .createdBy(book.getCreatedBy())
                .createdAt(book.getCreatedAt())
                .updatedBy(book.getLastModifiedBy())
                .updatedAt(book.getUpdatedAt())
//                .owner(book.getOwner().getFullName())
                .owner(book.getCreatedBy())
                .rate(book.getRate())
                .cover(FileUtils.readFileFromLocation(book.getBookCover()))
                .build();
    }

}
