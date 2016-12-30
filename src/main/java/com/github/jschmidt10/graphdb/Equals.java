package com.github.jschmidt10.graphdb;

public class Equals extends Criteria {

    private final Object value;

    public Equals(String name, Object value) {
        super(name);
        this.value = value;
    }

    @Override
    public boolean matches(Object value) {
        return this.value.equals(value);
    }
}
