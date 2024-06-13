package dev.arena.book_network.exceptions;

import dev.arena.book_network.dto.ErrorResponse;
import jakarta.mail.internet.AddressException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import static java.util.Map.entry;

import java.io.IOException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = Map.ofEntries(
            entry(MethodArgumentNotValidException.class, HttpStatus.UNPROCESSABLE_ENTITY),
            entry(MethodArgumentTypeMismatchException.class, HttpStatus.NOT_FOUND),
            entry(IllegalStateException.class, HttpStatus.FORBIDDEN),
            entry(ArrayIndexOutOfBoundsException.class, HttpStatus.INTERNAL_SERVER_ERROR),
            entry(NotFoundEntityException.class, HttpStatus.NOT_FOUND),
            entry(AddressException.class, HttpStatus.INTERNAL_SERVER_ERROR),
            entry(InvalidTokenException.class, HttpStatus.BAD_REQUEST),
            entry(DisabledException.class, HttpStatus.UNAUTHORIZED),
            entry(LockedException.class, HttpStatus.UNAUTHORIZED),
            entry(BadCredentialsException.class, HttpStatus.UNAUTHORIZED),
            entry(OperationNotPermittedException.class, HttpStatus.UNAUTHORIZED),
            entry(IOException.class, HttpStatus.INTERNAL_SERVER_ERROR)
    );

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalStateException.class,
            ArrayIndexOutOfBoundsException.class,
            NotFoundEntityException.class,
            AddressException.class,
            InvalidTokenException.class,
            DisabledException.class,
            LockedException.class,
            BadCredentialsException.class,
            OperationNotPermittedException.class,
            IOException.class
    })
    public ResponseEntity<ErrorResponse> handleExceptions(Exception exception, HttpServletRequest request) {
        return ErrorResponseBuilder.build(EXCEPTION_STATUS_MAP, exception, request);
    }

}
