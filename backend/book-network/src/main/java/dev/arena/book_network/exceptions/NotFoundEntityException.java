package dev.arena.book_network.exceptions;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message) {
        super(message);
    }
}
