package org.descheemaeker.tom.eurderproject.exception;

public class RequiredFieldIsNullException extends RuntimeException {
    public RequiredFieldIsNullException() {
        super("A field was left empty.");
    }
}
