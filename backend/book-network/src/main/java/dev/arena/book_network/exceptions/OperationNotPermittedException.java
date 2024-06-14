package dev.arena.book_network.exceptions;

public class OperationNotPermittedException extends RuntimeException{
    public OperationNotPermittedException(String message) {
        super(message);
    }
}
