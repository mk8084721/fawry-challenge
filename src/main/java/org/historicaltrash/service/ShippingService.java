package org.historicaltrash.service;

import org.historicaltrash.model.Shippable;
import org.historicaltrash.model.product.Product;

import java.util.List;

public class ShippingService {
    public void shipItems(List<Shippable> shippableItems) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (Shippable item : shippableItems) {
            System.out.printf("%dx %s %s%n",
                    getItemQuantity(shippableItems, item),
                    ((Product) item).getName(),
                    item.getShippingInfo());
            totalWeight += item.getWeight();
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }

    private long getItemQuantity(List<Shippable> items, Shippable item) {
        return items.stream().filter(i -> i.equals(item)).count();
    }
}
