package com.github.jschmidt10.graphdb;

import java.util.*;

/**
 * A breadth first graph walk.
 */
public class GraphWalk {

    private final GraphVisitor visitor;

    public GraphWalk(GraphVisitor visitor) {
        this.visitor = visitor;
    }

    /**
     * Walks more than one component.
     *
     * @param components
     */
    public void walk(Collection<Component> components) {
        Set<Component> visited = new TreeSet<>();
        components.forEach(c -> walkSingle(c, visited));
    }

    /**
     * Walks the graph with the configured visitor
     *
     * @param component
     */
    public void walk(Component component) {
        walkSingle(component, new TreeSet<>());
    }

    private void walkSingle(Component component, Set<Component> visited) {
        Queue<Component> toVisit = new LinkedList<>();

        toVisit.add(component);

        while (!toVisit.isEmpty()) {
            Component cur = toVisit.poll();
            visitor.visit(cur);
            visited.add(cur);

            cur.getConnected().forEach(c -> {
                if (!visited.contains(c)) {
                    toVisit.add(c);
                }
            });
        }
    }
}
