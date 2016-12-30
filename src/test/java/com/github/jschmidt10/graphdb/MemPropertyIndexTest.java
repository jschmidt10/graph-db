package com.github.jschmidt10.graphdb;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MemPropertyIndexTest {

    private PropertyIndex index = new MemPropertyIndex();
    private Key tom = new Key("tom");
    private Key tina = new Key("tina");
    private Key gerald = new Key("gerald");

    @Before
    public void setup() {
        index.add(tom, "gender", "male");
        index.add(tina, "gender", "female");
        index.add(gerald, "gender", "male");
    }

    @Test
    public void shouldAddAndRetrieveKeys() {
        Set<Key> results = index.getKeys("gender", "male");

        assertThat(results.contains(tom), is(true));
        assertThat(results.contains(tina), is(false));
        assertThat(results.contains(gerald), is(true));
    }

    @Test
    public void shouldComputeSelectivity() {
        assertEquals(index.getSelectivity("gender"), 2.0 / 3.0, 1e-10);
    }
}
