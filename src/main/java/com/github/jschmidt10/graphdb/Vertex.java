package com.github.jschmidt10.graphdb;

import java.util.List;

/**
 * An vertex of the graph.
 */
public interface Vertex extends Component {

    /**
     * Gets inbound edges
     *
     * @return edges
     */
    List<Edge> getInbound();

    /**
     * Gets outbound edges
     *
     * @return edges
     */
    List<Edge> getOutbound();

    /**
     * Adds a new inbound edge.
     *
     * @param edge
     */
    void addInbound(Edge edge);

    /**
     * Adds a new outbound edge.
     *
     * @param edge
     */
    void addOutbound(Edge edge);
}
