package com.github.jschmidt10.graphdb;

import java.util.*;

/**
 * A lazily evaluated edge.
 */
public class LazyMemEdge implements Edge {

    private final GraphStore store;
    private final Key key;
    private final Map<String, Object> properties;
    private boolean isHydrated = false;

    private Vertex in;
    private Vertex out;
    private Set<Component> connected;

    public LazyMemEdge(GraphStore store, Key key) {
        this.store = store;
        this.key = key;
        this.properties = new HashMap<>();
    }

    private void ensureHydrated() {
        if (!isHydrated) {
            Edge edge = (Edge) store.get(key);
            edge.getProperties().forEach(this::setProperty);
            this.in = edge.getIn();
            this.out = edge.getOut();

            Set<Component> comps = new TreeSet<>();
            comps.add(in);
            comps.add(out);
            this.connected = Collections.unmodifiableSet(comps);

            isHydrated = true;
        }
    }

    @Override
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    @Override
    public Key getKey() {
        return key;
    }

    @Override
    public Map<String, Object> getProperties() {
        ensureHydrated();
        return properties;
    }

    @Override
    public Vertex getIn() {
        ensureHydrated();
        return in;
    }

    @Override
    public Vertex getOut() {
        ensureHydrated();
        return out;
    }

    @Override
    public Set<Component> getConnected() {
        ensureHydrated();
        return connected;
    }

    @Override
    public int compareTo(Component o) {
        return key.compareTo(o.getKey());
    }
}
