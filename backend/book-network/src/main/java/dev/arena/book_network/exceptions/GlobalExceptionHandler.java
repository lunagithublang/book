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

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
            MethodArgumentNotValidException.class, HttpStatus.UNPROCESSABLE_ENTITY,
            MethodArgumentTypeMismatchException.class, HttpStatus.NOT_FOUND,
            IllegalStateException.class, HttpStatus.FORBIDDEN,
            ArrayIndexOutOfBoundsException.class, HttpStatus.INTERNAL_SERVER_ERROR,
            NotFoundEntityException.class, HttpStatus.NOT_FOUND,
            AddressException.class, HttpStatus.INTERNAL_SERVER_ERROR,
            InvalidTokenException.class, HttpStatus.BAD_REQUEST,
            DisabledException.class, HttpStatus.UNAUTHORIZED,
            LockedException.class, HttpStatus.UNAUTHORIZED,
            BadCredentialsException.class, HttpStatus.UNAUTHORIZED
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
            BadCredentialsException.class
    })
    public ResponseEntity<ErrorResponse> handleExceptions(Exception exception, HttpServletRequest request) {
        return ErrorResponseBuilder.build(EXCEPTION_STATUS_MAP, exception, request);
    }

}
