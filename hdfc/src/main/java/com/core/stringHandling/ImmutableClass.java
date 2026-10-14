package com.core.stringHandling;

import java.util.List;

public final class ImmutableClass {
    private  int id;
    private String name;
    private List<String> list;

    public ImmutableClass(int id, String name, List<String> list){
        this.id = id;
        this.name = name;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getList() {
        return list;
    }
}
