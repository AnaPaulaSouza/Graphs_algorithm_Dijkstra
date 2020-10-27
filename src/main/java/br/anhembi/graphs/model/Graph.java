package br.anhembi.graphs.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes;

    public Graph(Collection<Node> nodes) {
        this.nodes = new HashSet<>(nodes);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

}
