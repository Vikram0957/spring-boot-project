package com.core.designpattern.factory;

public class FactoryClass {
    public static PaymentMode getObject(String mode) {

        return switch (mode) {
            case "UPI" -> new UPIPayment();
            case "CASH" -> new CashMode();
            default -> throw new IllegalArgumentException(
                    "Unsupported payment type: " + mode);
        };
    }
}
