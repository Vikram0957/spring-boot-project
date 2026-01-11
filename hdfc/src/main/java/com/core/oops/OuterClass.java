package com.core.oops;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OuterClass implements Cloneable{
    int id;
    String name;
    Address address;

    @Override
    public Object clone() throws CloneNotSupportedException {
        OuterClass clone = (OuterClass)super.clone();
        clone.address = new Address(this.address.getAddress());
        return super.clone();
    }
}
