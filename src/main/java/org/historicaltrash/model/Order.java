package org.historicaltrash.model;

import org.historicaltrash.model.product.Product;

import java.util.Map;

public class Order {
    private final Map<Product, Integer> items;
    private final double subtotal;
    private final double shippingFee;
    private final double total;

    public Order(Map<Product, Integer> items, double subtotal, double shippingFee) {
        this.items = items;
        this.subtotal = subtotal;
        this.shippingFee = shippingFee;
        this.total = subtotal + shippingFee;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public double getTotal() {
        return total;
    }
}
