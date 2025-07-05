package org.historicaltrash.model.product;

import org.historicaltrash.model.Expirable;
import org.historicaltrash.model.Shippable;

import java.time.LocalDate;

public class ExpirableShippableProduct extends Product implements Expirable, Shippable {
    private final LocalDate expiryDate;
    private final double weight;

    public ExpirableShippableProduct(String name, double price, int quantity,
                                     LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
