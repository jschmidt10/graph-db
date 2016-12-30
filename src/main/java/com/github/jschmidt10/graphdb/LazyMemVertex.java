package com.github.jschmidt10.graphdb;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A lazily evaluated vertex.
 */
public class LazyMemVertex extends DefaultVertex {

    private final GraphStore store;
    private boolean isHydrated = false;

    public LazyMemVertex(GraphStore store, Key k) {
        super(k);
        this.store = store;
    }

    private void ensureHydrated() {
        if (!isHydrated) {
            Vertex vertex = (Vertex) store.get(getKey());
            vertex.getProperties().forEach(this::setProperty);
            vertex.getInbound().forEach(this::addInbound);
            vertex.getOutbound().forEach(this::addOutbound);
            isHydrated = true;
        }
    }

    @Override
    public List<Edge> getInbound() {
        ensureHydrated();
        return super.getInbound();
    }

    @Override
    public List<Edge> getOutbound() {
        ensureHydrated();
        return super.getOutbound();
    }

    @Override
    public Map<String, Object> getProperties() {
        ensureHydrated();
        return super.getProperties();
    }

    @Override
    public Set<Component> getConnected() {
        ensureHydrated();
        return super.getConnected();
    }
}
