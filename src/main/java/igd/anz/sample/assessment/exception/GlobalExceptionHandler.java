package igd.anz.sample.assessment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<Object> accountNotFoundException(AccountNotFoundException exception) {
        logException(exception, false);
        return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(AccountNotFoundException exception) {
        logException(exception, false);
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    private void logException(Throwable exception, boolean error){
        if (error) {
            log.error("message:\"Account infor service excountered an exception\", exception=", exception);
        } else {
            log.info("message:\"Account infor service excountered an exception\", exception=", exception);
        }
    }
}
