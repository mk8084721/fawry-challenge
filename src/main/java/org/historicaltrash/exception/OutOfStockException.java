package org.historicaltrash.exception;

public class OutOfStockException extends Exception {
    public OutOfStockException(String productName) {
        super(String.format("Product '%s' is out of stock", productName));
    }
}
