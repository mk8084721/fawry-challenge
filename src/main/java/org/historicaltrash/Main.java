package org.historicaltrash;

import org.historicaltrash.model.*;
import org.historicaltrash.model.product.*;
import org.historicaltrash.service.ShippingService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product product = new RegularProduct("regular Product",3.00,5);

        Product expiredProduct = new ExpirableProduct("Cheese",3.00,5, LocalDate.of(2026,3,2));
        System.out.println(expiredProduct instanceof Expirable ? ((ExpirableProduct) expiredProduct).getExpiryDate() : "not Expirable");

        Product shippableProduct = new ShippableProduct("Car",3.00,5, 44.00);
        System.out.println(shippableProduct instanceof Shippable ? ((Shippable) shippableProduct).getWeight() : "not Shippable");

        ExpirableShippableProduct expirableShippableProduct = new ExpirableShippableProduct("Fridge",3.00,5,
                LocalDate.of(2026,3,2), 55.0);
        System.out.println((expirableShippableProduct instanceof Shippable && expirableShippableProduct instanceof Expirable)?
                ((ExpirableShippableProduct) expirableShippableProduct).getWeight()+"\n"+
                        ((ExpirableShippableProduct) expirableShippableProduct).getExpiryDate() : "not ExpirableShippableProduct");

        ShippingService shippingService = new ShippingService();
        //shippingService.shipItems(List.of(expirableShippableProduct,shippableProduct));

    }
}