package com.github.jschmidt10.graphdb;

import java.util.Map;
import java.util.Set;

/**
 * A component of a graph.
 */
public interface Component extends Comparable<Component> {

    /**
     * Gets the components key
     *
     * @return key
     */
    Key getKey();

    /**
     * Gets the components properties.
     *
     * @return properties
     */
    Map<String, Object> getProperties();

    /**
     * Sets a property
     *
     * @param key
     * @param value
     */
    void setProperty(String key, Object value);

    /**
     * Gets components adjacent to this one.
     *
     * @return components
     */
    Set<Component> getConnected();
}
