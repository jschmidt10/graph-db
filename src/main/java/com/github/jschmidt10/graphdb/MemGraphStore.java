package com.github.jschmidt10.graphdb;

import org.javatuples.Triplet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An in memory graph store implementation.
 */
public class MemGraphStore implements GraphStore {

    // Triplet<Props, InboundEdges, OutboundEdges>
    private Map<Key, Triplet<Map<String, Object>, Set<Key>, Set<Key>>> vertices = new HashMap<>();

    // Triplet<Props, InVertex, OutVertex>
    private Map<Key, Triplet<Map<String, Object>, Key, Key>> edges = new HashMap<>();

    @Override
    public Component get(Key key) {
        Triplet<Map<String, Object>, Set<Key>, Set<Key>> v = vertices.get(key);

        if (v != null) {
            return buildVertex(key, v);
        }

        Triplet<Map<String, Object>, Key, Key> edge = edges.get(key);
        if (edge != null) {
            return buildEdge(key, edge);
        }

        return null;
    }

    private Component buildEdge(Key key, Triplet<Map<String, Object>, Key, Key> edge) {
        Vertex in = new LazyMemVertex(this, edge.getValue1());
        Vertex out = new LazyMemVertex(this, edge.getValue2());

        return new DefaultEdge(key, in, out, edge.getValue0());
    }

    private Component buildVertex(Key key, Triplet<Map<String, Object>, Set<Key>, Set<Key>> vertex) {
        Vertex v = new DefaultVertex(key, vertex.getValue0());
        vertex.getValue1().forEach(k -> v.addInbound(new LazyMemEdge(this, k)));
        vertex.getValue2().forEach(k -> v.addOutbound(new LazyMemEdge(this, k)));

        return v;
    }

    @Override
    public void update(Component component) {
        new GraphWalk(this::executeUpdates).walk(component);
    }

    private void executeUpdates(Component component) {
        if (component instanceof Vertex) {
            updateVertex((Vertex) component);
        } else if (component instanceof Edge) {
            updateEdge((Edge) component);
        } else {
            throw new IllegalArgumentException("Expected a Vertex or Edge but received a " + component);
        }
    }

    private void updateEdge(Edge edge) {
        Triplet<Map<String, Object>, Key, Key> e = edges.get(edge.getKey());

        if (e == null) {
            e = Triplet.with(edge.getProperties(), edge.getIn().getKey(), edge.getOut().getKey());
            edges.put(edge.getKey(), e);
        } else {
            e.getValue0().putAll(edge.getProperties());
        }
    }

    private void updateVertex(Vertex vertex) {
        Triplet<Map<String, Object>, Set<Key>, Set<Key>> v = vertices.get(vertex.getKey());

        if (v == null) {
            Set<Key> in = vertex.getInbound().stream().map(Component::getKey).collect(Collectors.toSet());
            Set<Key> out = vertex.getOutbound().stream().map(Component::getKey).collect(Collectors.toSet());
            v = Triplet.with(vertex.getProperties(), in, out);
            vertices.put(vertex.getKey(), v);
        } else {
            v.getValue0().putAll(vertex.getProperties());
        }
    }
}
