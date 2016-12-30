package com.github.jschmidt10.graphdb;

import java.util.*;

/**
 * A simple memory based property index.
 */
public class MemPropertyIndex implements PropertyIndex {

    private Map<String, Long> counts = new HashMap<>();
    private Map<String, Set<Object>> distinct = new HashMap<>();
    private Map<Property, Set<Key>> index = new HashMap<>();

    @Override
    public double getSelectivity(String propertyName) {
        long t = counts.getOrDefault(propertyName, 1L);
        long d = distinct.getOrDefault(propertyName, Collections.emptySet()).size();
        return ((double) d) / t;
    }

    @Override
    public Set<Key> getKeys(String name, Object value) {
        return index.getOrDefault(new Property(name, value), new TreeSet<>());
    }

    @Override
    public void add(Key key, String name, Object value) {
        index.computeIfAbsent(new Property(name, value), p -> new TreeSet<>()).add(key);
        distinct.computeIfAbsent(name, p -> new TreeSet<>()).add(value);
        counts.merge(name, 1L, (a, b) -> a + b);
    }

    private class Property {
        private String name;
        private Object value;

        public Property(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Property property = (Property) o;

            if (name != null ? !name.equals(property.name) : property.name != null) return false;
            return value != null ? value.equals(property.value) : property.value == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }
}
