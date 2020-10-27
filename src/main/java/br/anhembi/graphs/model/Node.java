package br.anhembi.graphs.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Node {

    final private String label;
    private Map<Node, Integer> adjacentNodesWithCost;

    public Node(final String label) {
        this.label = label;
        this.adjacentNodesWithCost = new HashMap<>();
    }

    public String getLabel() {
        return label;
    }

    public Map<Node, Integer> getAdjacentNodesWithCost() {
        return adjacentNodesWithCost;
    }

    public void addAdjacentNodeWithCost(final Node node, final int cost) {
        adjacentNodesWithCost.put(node, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Node)) {
            return false;
        }

        final Node node = (Node) o;
        return Objects.equals(label, node.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return label;
    }
}