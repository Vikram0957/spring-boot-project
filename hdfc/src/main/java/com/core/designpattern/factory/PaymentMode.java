package com.core.designpattern.factory;

import lombok.AllArgsConstructor;

public interface PaymentMode {
    @AllArgsConstructor
    class Payment {
        private String mode;
    }

    public Payment modeOfPayment(String mode);
}
