package org.historicaltrash.model;

import org.historicaltrash.exception.OutOfStockException;
import org.historicaltrash.model.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addItem(Product product, int quantity) throws OutOfStockException {
        if (quantity > product.getQuantity()) {
            throw new OutOfStockException(product.getName());
        }
        items.merge(product, quantity, Integer::sum);
    }

    public Map<Product, Integer> getItems() {
        return new HashMap<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
