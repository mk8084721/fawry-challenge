package org.historicaltrash;

import org.historicaltrash.model.*;
import org.historicaltrash.model.product.*;
import org.historicaltrash.service.CheckoutService;
import org.historicaltrash.service.ShippingService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create products
        Product tv = new ShippableProduct("TV", 599.99, 10, 15.5);
        Product mobile = new RegularProduct("Mobile", 399.99, 15);
        Product cheese = new ExpirableShippableProduct("Cheese", 4.99, 50,
                LocalDate.now().plusDays(10), 0.4);
        Product biscuits = new ExpirableProduct("Biscuits", 2.49, 100,
                LocalDate.now().minusDays(1)); // Expired
        Product scratchCard = new RegularProduct("Mobile Scratch Card", 10.00, 200);

        // Create customer
        Customer customer = new Customer("John Doe", 1000.00);

        // Create cart
        Cart cart = new Cart();

        try {
            // Add items to cart
            cart.addItem(cheese, 2);
            cart.addItem(tv, 1);
            cart.addItem(scratchCard, 1);
            // cart.addItem(biscuits, 1); // Would throw ProductExpiredException
            // cart.addItem(tv, 20); // Would throw OutOfStockException

            // Checkout
            ShippingService shippingService = new ShippingService();
            CheckoutService checkoutService = new CheckoutService(shippingService);
            Order order = checkoutService.checkout(customer, cart);

            // Print receipt
            printReceipt(order, customer);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printReceipt(Order order, Customer customer) {
        System.out.println("** Checkout receipt **");
        order.getItems().forEach((product, quantity) -> {
            System.out.printf("%dx %s %.2f%n",
                    quantity, product.getName(), product.getPrice() * quantity);
        });

        System.out.println("----------------------");
        System.out.printf("Subtotal %.2f%n", order.getSubtotal());
        System.out.printf("Shipping %.2f%n", order.getShippingFee());
        System.out.printf("Amount %.2f%n", order.getTotal());
        System.out.printf("Remaining balance: %.2f%n", customer.getBalance());
    }
}