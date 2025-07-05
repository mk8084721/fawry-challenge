package org.historicaltrash.model;

public interface Shippable {
    double getWeight();

    default String getShippingInfo() {
        return String.format("%.0fg", getWeight() * 1000);
    }
}
