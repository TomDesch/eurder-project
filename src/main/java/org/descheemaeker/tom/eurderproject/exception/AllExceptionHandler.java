package org.descheemaeker.tom.eurderproject.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class AllExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllExceptionHandler.class);

    @ExceptionHandler(RequiredFieldIsNullException.class)
    protected void emailAddressInvalidException(RequiredFieldIsNullException exception, HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED.value(), exception.getMessage());
        LOGGER.error(exception.getMessage(), exception);
    }
}