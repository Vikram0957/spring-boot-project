package com.core.designpattern.builder;

public class House {
    private final int houseNumber;
    private final String name;
    private final String address;
    private final int pinCode;
    private final String landmark;
    private final String owner;

     private House(Builder builder) {
        this.houseNumber = builder.houseNumber;
        this.name = builder.name;
        this.address = builder.address;
        this.pinCode = builder.pinCode;
        this.landmark = builder.landmark;
        this.owner = builder.owner;
    }

    static class Builder{
        private int houseNumber;
        private String name;
        private String address;
        private int pinCode;
        private String landmark;
        private String owner;

        public Builder houseNumber(int houseNumber){
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }

        public Builder pinCode(int pinCode){
            this.pinCode = pinCode;
            return this;
        }

        public Builder landmark(String landmark){
            this.landmark = landmark;
            return this;
        }

        public Builder owner(String owner){
            this.owner = owner;
            return this;
        }

        public House build(){
            return new House(this);
        }
    }

    @Override
    public String toString() {
        return "House{" +
                "houseNumber=" + houseNumber +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pinCode=" + pinCode +
                ", landmark='" + landmark + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
