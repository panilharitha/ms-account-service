package igd.anz.assessment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccountNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiError> accountNotFoundException(AccountNotFoundException exception) {
        logException(exception, exception.getApiError().getMessage(), false);
        return new ResponseEntity<>(ApiError.builder().message(exception.getApiError().getMessage()).build(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TransactionNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> userNotFoundException(TransactionNotFoundException exception) {
        logException(exception, exception.getApiError().getMessage(), false);
        return new ResponseEntity<>(ApiError.builder().message(exception.getApiError().getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, ConstraintViolationException.class, HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public ResponseEntity<ApiError> handleValidationException(Exception exception) {
        logException(exception, exception.getMessage(), true);
        return new ResponseEntity<>(ApiError.builder().message(exception.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    private void logException(Throwable exception, String message, boolean error){
        if (error) {
            log.error(String.format("message:\"Account info service encountered an exception\", exception=%s", exception.getMessage()));
        } else {
            log.warn(String.format("message:\"Account info service encountered an exception\", exception=%s", exception.getMessage()));
        }
    }
}
