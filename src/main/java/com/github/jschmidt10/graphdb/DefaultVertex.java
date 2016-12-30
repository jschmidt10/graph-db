package com.github.jschmidt10.graphdb;

import java.util.*;

/**
 * A vertex implmentation.
 */
public class DefaultVertex implements Vertex {

    private final Key key;
    private Map<String, Object> properties = new TreeMap<>();
    private List<Edge> inboundEdges = new LinkedList<>();
    private List<Edge> outboundEdges = new LinkedList<>();

    public DefaultVertex(Key key) {
        this.key = key;
    }

    public DefaultVertex(Key key, Map<String, Object> properties) {
        this(key);
        this.properties = properties;
    }

    @Override
    public Key getKey() {
        return key;
    }

    @Override
    public void setProperty(String name, Object value) {
        properties.put(name, value);
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public void addInbound(Edge edge) {
        inboundEdges.add(edge);
    }

    @Override
    public List<Edge> getInbound() {
        return inboundEdges;
    }

    @Override
    public void addOutbound(Edge edge) {
        outboundEdges.add(edge);
    }

    @Override
    public List<Edge> getOutbound() {
        return outboundEdges;
    }

    @Override
    public Set<Component> getConnected() {
        Set<Component> components = new TreeSet<>();
        components.addAll(inboundEdges);
        components.addAll(outboundEdges);
        return components;
    }

    @Override
    public int compareTo(Component o) {
        return key.compareTo(o.getKey());
    }
}
