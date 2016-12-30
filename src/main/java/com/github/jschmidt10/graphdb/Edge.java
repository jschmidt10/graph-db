package com.github.jschmidt10.graphdb;

/**
 * An edge of the graph.
 */
public interface Edge extends Component {

    /**
     * Gets the "in" vertex
     *
     * @return vertex
     */
    Vertex getIn();

    /**
     * Gets the "out" vertex
     *
     * @return vertex
     */
    Vertex getOut();
}
