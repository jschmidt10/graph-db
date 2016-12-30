package com.github.jschmidt10.graphdb;

public class Key implements Comparable<Key> {

    private final String k;

    public Key(String k) {
        if (k == null)
            throw new IllegalArgumentException("Key cannot accept null");
        this.k = k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        return k != null ? k.equals(key.k) : key.k == null;

    }

    @Override
    public int hashCode() {
        return k != null ? k.hashCode() : 0;
    }

    @Override
    public int compareTo(Key o) {
        return k.compareTo(o.k);
    }
}
