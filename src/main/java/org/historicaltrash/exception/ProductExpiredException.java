package org.historicaltrash.exception;

public class ProductExpiredException extends Exception {
    public ProductExpiredException(String productName) {
        super(String.format("Product '%s' has expired", productName));
    }
}