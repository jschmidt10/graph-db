package com.github.jschmidt10.graphdb;

/**
 * Stores graph vertices and edges.
 */
public interface GraphStore {

    /**
     * Gets a component by id.
     *
     * @param key
     * @return component
     */
    Component get(Key key);

    /**
     * Updates the graph with the given component and any connected components.
     *
     * @param component
     */
    void update(Component component);
}
