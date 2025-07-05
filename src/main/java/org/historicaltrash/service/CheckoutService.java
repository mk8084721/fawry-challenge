package org.historicaltrash.service;

import org.historicaltrash.exception.CartEmptyException;
import org.historicaltrash.exception.InsufficientBalanceException;
import org.historicaltrash.exception.OutOfStockException;
import org.historicaltrash.exception.ProductExpiredException;
import org.historicaltrash.model.*;
import org.historicaltrash.model.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutService {
    private static final double SHIPPING_RATE_PER_KG = 10.0;
    private final ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public Order checkout(Customer customer, Cart cart) throws CartEmptyException,
            InsufficientBalanceException, OutOfStockException, ProductExpiredException {

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        Map<Product, Integer> items = cart.getItems();
        validateProducts(items);

        double subtotal = calculateSubtotal(items);
        double shippingFee = calculateShippingFee(items);
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            throw new InsufficientBalanceException(customer.getBalance(), total);
        }

        // Process order
        updateInventory(items);
        customer.deductBalance(total);
        Order order = new Order(items, subtotal, shippingFee);

        // Process shipping
        processShipping(items);

        // Clear cart
        cart.clear();

        return order;
    }

    private void validateProducts(Map<Product, Integer> items)
            throws OutOfStockException, ProductExpiredException {

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (quantity > product.getQuantity()) {
                throw new OutOfStockException(product.getName());
            }

            if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                throw new ProductExpiredException(product.getName());
            }
        }
    }

    private double calculateSubtotal(Map<Product, Integer> items) {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private double calculateShippingFee(Map<Product, Integer> items) {
        return items.keySet().stream()
                .filter(product -> product instanceof Shippable)
                .mapToDouble(product -> ((Shippable) product).getWeight() * SHIPPING_RATE_PER_KG)
                .sum();
    }

    private void updateInventory(Map<Product, Integer> items) throws OutOfStockException {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            product.reduceQuantity(entry.getValue());
        }
    }

    private void processShipping(Map<Product, Integer> items) {
        List<Shippable> shippableItems = new ArrayList<>();

        items.forEach((product, quantity) -> {
            if (product instanceof Shippable) {
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        });

        if (!shippableItems.isEmpty()) {
            shippingService.shipItems(shippableItems);
        }
    }
}