package org.descheemaeker.tom.eurderproject.exception;

public class LoginCredentialsMismatchException extends RuntimeException {
    public LoginCredentialsMismatchException() {
        super("There is no account found with this username and password combination.");
    }
}
