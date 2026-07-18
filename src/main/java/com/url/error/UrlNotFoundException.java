package com.url.error;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String message) {
        super("No Url found for this error: " + message);
    }
}
