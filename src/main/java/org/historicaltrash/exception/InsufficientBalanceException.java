package org.historicaltrash.exception;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(double currentBalance, double requiredAmount) {
        super(String.format("Insufficient balance. Current: %.2f, Required: %.2f",
                currentBalance, requiredAmount));
    }
}