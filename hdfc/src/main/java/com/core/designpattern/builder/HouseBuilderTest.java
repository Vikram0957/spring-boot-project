package com.core.designpattern.builder;

public class HouseBuilderTest {
    public static void main(String[] args) {
        House house = new House.Builder()
                .houseNumber(101)
                .name("Green Villa")
                .address("Mumbai, Maharashtra")
                .pinCode(400001)
                .landmark("Near Railway Station")
                .owner("Vikram")
                .build();

        House build = new House.Builder()
                .houseNumber(202)
                .address("Satna")
                .landmark("Near landmark")
                .pinCode(123456)
                .build();

        System.out.println(house);
        System.out.println(build);
    }
}
