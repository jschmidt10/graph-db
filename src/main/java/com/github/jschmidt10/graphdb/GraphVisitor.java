package com.github.jschmidt10.graphdb;

/**
 * A visitor for any component in the graph.
 */
public interface GraphVisitor {

    /**
     * Visit the component
     *
     * @param component
     */
    void visit(Component component);
}
