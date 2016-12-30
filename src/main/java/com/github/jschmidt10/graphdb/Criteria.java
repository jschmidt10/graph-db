package com.github.jschmidt10.graphdb;

public abstract class Criteria {

    private final String name;

    public Criteria(String name) {
        this.name = name;
    }

    public abstract boolean matches(Object value);

    public String getName() {
        return name;
    }
}
