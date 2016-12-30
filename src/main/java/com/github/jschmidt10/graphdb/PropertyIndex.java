package com.github.jschmidt10.graphdb;

import java.util.Set;

/**
 * Index of propertys to object keys.
 */
public interface PropertyIndex {

    /**
     * Gets the selectivity of a property.
     *
     * @param propertyName
     * @return selectivity
     */
    double getSelectivity(String propertyName);

    /**
     * Gets the keys that match the given property/value.
     *
     * @param name
     * @param value
     * @return keys
     */
    Set<Key> getKeys(String name, Object value);

    /**
     * Add a new key to the index.
     *
     * @param key
     * @param name
     * @param value
     */
    void add(Key key, String name, Object value);
}
