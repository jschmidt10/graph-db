package com.github.jschmidt10.graphdb;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GraphWalkTest {

    private Vertex v1 = new DefaultVertex(new Key("v1"));
    private Vertex v2 = new DefaultVertex(new Key("v2"));
    private Vertex v3 = new DefaultVertex(new Key("v3"));
    private Edge e1 = new DefaultEdge(new Key("e1"), v1, v2);
    private Edge e2 = new DefaultEdge(new Key("e2"), v2, v3);

    private Vertex v4 = new DefaultVertex(new Key("v4"));
    private Vertex v5 = new DefaultVertex(new Key("v5"));
    private Edge e3 = new DefaultEdge(new Key("e3"), v4, v5);

    @Test
    public void shouldVisitAllComponents() {
        final Set<Component> visited = new TreeSet<>();
        GraphVisitor visitor = (component -> visited.add(component));

        GraphWalk graphWalk = new GraphWalk(visitor);
        graphWalk.walk(Arrays.asList(v1, v2, v4));

        assertThat(visited.contains(v1), is(true));
        assertThat(visited.contains(v2), is(true));
        assertThat(visited.contains(v3), is(true));
        assertThat(visited.contains(v4), is(true));
        assertThat(visited.contains(v5), is(true));
        assertThat(visited.contains(e1), is(true));
        assertThat(visited.contains(e2), is(true));
        assertThat(visited.contains(e3), is(true));
        assertThat(visited.size(), is(8));
    }
}
