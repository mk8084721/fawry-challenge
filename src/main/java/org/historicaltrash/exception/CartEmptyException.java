package org.historicaltrash.exception;

public class CartEmptyException extends Exception {
    public CartEmptyException() {
        super("Cannot checkout with an empty cart");
    }
}
