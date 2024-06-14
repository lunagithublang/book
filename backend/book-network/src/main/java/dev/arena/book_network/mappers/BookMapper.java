package dev.arena.book_network.mappers;

import dev.arena.book_network.dto.book.BookRequest;
import dev.arena.book_network.dto.book.BookResponse;
import dev.arena.book_network.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toBook(BookRequest bookRequest);

    BookResponse toResponse(Book book);
}
