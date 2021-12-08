package org.descheemaeker.tom.eurderproject.exception;

public class NoAccessToFeatureException extends RuntimeException {
    public NoAccessToFeatureException(String email) {
        super(email + " does not have access to this feature.");
    }
}
