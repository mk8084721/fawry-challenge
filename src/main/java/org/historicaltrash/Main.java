package org.historicaltrash;

import org.historicaltrash.model.Expirable;
import org.historicaltrash.model.ExpirableProduct;
import org.historicaltrash.model.Product;
import org.historicaltrash.model.RegularProduct;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product product = new RegularProduct("regular Product",3.00,5);
        Product expiredProduct = new ExpirableProduct("Cheese",3.00,5, LocalDate.of(2026,3,2));
        System.out.println(expiredProduct instanceof Expirable ? ((ExpirableProduct) expiredProduct).getExpiryDate() : "not Expirable");
    }
}