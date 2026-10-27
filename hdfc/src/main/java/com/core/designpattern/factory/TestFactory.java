package com.core.designpattern.factory;

public class TestFactory {
    public static void main(String[] args) {
        PaymentMode upi = FactoryClass.getObject("UPI");
        System.out.println(upi);

        PaymentMode cash = FactoryClass.getObject("CASH");
        System.out.println(cash);

        PaymentMode cheque = FactoryClass.getObject("CHEQUE");
        System.out.println(cheque);
    }
}
