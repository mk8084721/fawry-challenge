package org.historicaltrash.model.product;

import org.historicaltrash.exception.OutOfStockException;

public abstract class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) throws OutOfStockException {
        if (amount > quantity) {
            throw new OutOfStockException(name);
        }
        quantity -= amount;
    }
}
