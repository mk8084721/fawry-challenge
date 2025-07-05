package org.historicaltrash.model.product;

import org.historicaltrash.model.Expirable;

import java.time.LocalDate;

public class ExpirableProduct extends Product implements Expirable {
    private final LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
