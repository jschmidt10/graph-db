package com.github.jschmidt10.graphdb;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MemGraphStoreTest {

    private GraphStore graphStore = new MemGraphStore();

    @Test
    public void shouldAddAndFetchComponents() {
        Key johnKey = new Key("john");
        Key tinaKey = new Key("tina");
        Key edgeKey = new Key("e1");

        DefaultVertex john = new DefaultVertex(johnKey);
        john.setProperty("name", "john");
        john.setProperty("age", "30");

        DefaultVertex tina = new DefaultVertex(tinaKey);
        tina.setProperty("name", "tina");
        tina.setProperty("age", "28");

        DefaultEdge hasHusband = new DefaultEdge(edgeKey, tina, john);
        hasHusband.setProperty("rel", "hasHusband");
        hasHusband.setProperty("since", "2010");

        graphStore.update(hasHusband);

        Vertex v1 = (Vertex) graphStore.get(johnKey);
        assertThat(v1.getKey(), is(johnKey));
        assertThat(v1.getProperties().get("name"), is("john"));

        List<Edge> edges = v1.getInbound();
        Edge edge = edges.get(0);
        assertThat(edges.size(), is(1));
        assertThat(edge.getProperties().get("since"), is("2010"));

        Vertex v2 = edge.getIn();
        assertThat(v2.getKey(), is(tinaKey));
        assertThat(v2.getProperties().get("name"), is("tina"));
    }
}
