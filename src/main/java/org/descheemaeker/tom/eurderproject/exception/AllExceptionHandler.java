package org.descheemaeker.tom.eurderproject.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class AllExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllExceptionHandler.class);

    //todo : split exceptions up in packages based on use cases
    //todo : Make this class more efficient
    //todo : What error when adding user that already exists? (email not unique)
    //todo : What error when adding item that already exists? (item name not unique)


    @ExceptionHandler(RequiredFieldIsNullException.class)
    protected void requiredFieldIsNullException(RequiredFieldIsNullException exception, HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED.value(), exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
    }

    @ExceptionHandler(EmailDoesNotExistException.class)
    protected void emailDoesNotExistException(EmailDoesNotExistException exception, HttpServletResponse response) throws IOException {
        response.sendError(FORBIDDEN.value(), exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
    }

    @ExceptionHandler(LoginCredentialsMismatchException.class)
    protected void loginCredentialsMismatchException(LoginCredentialsMismatchException exception, HttpServletResponse response) throws IOException {
        response.sendError(FORBIDDEN.value(), exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
    }

    @ExceptionHandler(NoAccessToFeatureException.class)
    protected void noAccessToFeatureException(NoAccessToFeatureException exception, HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED.value(), exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
    }
}
