package com.core.designpattern.factory;

public class CashMode implements PaymentMode{
    @Override
    public Payment modeOfPayment(String mode) {
        return new Payment("CASH");
    }
}
