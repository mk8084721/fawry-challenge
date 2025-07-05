package org.historicaltrash.service;

import org.historicaltrash.model.Shippable;
import org.historicaltrash.model.product.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShippingService {
    public void shipItems(List<Shippable> shippableItems) {
        System.out.println("** Shipment notice **");

        // Group identical items and count quantities
        Map<Shippable, Long> groupedItems = shippableItems.stream()
                .collect(Collectors.groupingBy(
                        item -> item,
                        Collectors.counting()
                ));

        double totalWeight = 0;

        for (Map.Entry<Shippable, Long> entry : groupedItems.entrySet()) {
            Shippable item = entry.getKey();
            long quantity = entry.getValue();

            System.out.printf("%dx %s %s%n",
                    quantity,
                    ((Product)item).getName(),
                    item.getShippingInfo());

            totalWeight += item.getWeight() * quantity;
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
