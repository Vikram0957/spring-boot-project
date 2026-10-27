package com.core.designpattern.factory;

public class UPIPayment implements PaymentMode{
    @Override
    public Payment modeOfPayment(String mode) {
        return new Payment("UPI");
    }
}
