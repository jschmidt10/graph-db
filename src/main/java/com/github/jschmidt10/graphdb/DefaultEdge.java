package com.github.jschmidt10.graphdb;

import java.util.*;

/**
 * An edge.
 */
public class DefaultEdge implements Edge {

    private final Key key;
    private final Vertex in;
    private final Vertex out;
    private final Set<Component> connected;
    private Map<String, Object> properties = new TreeMap<>();

    public DefaultEdge(Key key, Vertex in, Vertex out) {
        this.key = key;
        this.in = in;
        this.out = out;

        Set<Component> comps = new TreeSet<>();
        comps.add(in);
        comps.add(out);
        this.connected = Collections.unmodifiableSet(comps);

        this.in.addOutbound(this);
        this.out.addInbound(this);
    }

    public DefaultEdge(Key key, Vertex in, Vertex out, Map<String, Object> properties) {
        this(key, in, out);
        this.properties = properties;
    }

    @Override
    public Vertex getIn() {
        return in;
    }

    @Override
    public Vertex getOut() {
        return out;
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
    public Set<Component> getConnected() {
        return connected;
    }

    @Override
    public int compareTo(Component o) {
        return key.compareTo(o.getKey());
    }
}
