package org.descheemaeker.tom.eurderproject.exception;

public class EmailDoesNotExistException extends RuntimeException {
    public EmailDoesNotExistException() {
        super("This email does not exist in our database.");
    }
}
