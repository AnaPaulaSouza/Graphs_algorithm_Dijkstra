package br.anhembi.graphs.model;

import br.anhembi.graphs.exceptions.LabelDoesNotExistException;

import java.util.*;
import java.util.stream.Collectors;

public class GraphBuilder {

    private Set<String> nodeLabels;
    private List<Edge> edges;
    private boolean isDirected;

    public GraphBuilder() {
        nodeLabels = new HashSet<>();
        edges = new ArrayList<>();
    }

    public GraphBuilder withNodeLabel(final String label) {
        nodeLabels.add(label);
        return this;
    }

    public GraphBuilder withEdge(final String source, final String target, final Integer cost) {
        edges.add(new Edge(source, target, (cost == null) ? 1 : cost));
        return this;
    }

    public GraphBuilder withIsDirected(final boolean isDirected) {
        this.isDirected = isDirected;
        return this;
    }

    public Graph build() throws LabelDoesNotExistException {
        final Map<String, Node> nodeByLabel =
                nodeLabels.stream()
                        .collect(Collectors.toMap(label -> label, Node::new));

        for (final Edge edge : edges) {
            final Node sourceNode = nodeByLabel.get(edge.getSource());
            if (sourceNode == null) {
                throw new LabelDoesNotExistException(edge.getSource());
            }

            final Node targetNode = nodeByLabel.get(edge.getTarget());
            if (targetNode == null) {
                throw new LabelDoesNotExistException(edge.getTarget());
            }

            sourceNode.addAdjacentNodeWithCost(targetNode, edge.getCost());

            if(!isDirected) {
                targetNode.addAdjacentNodeWithCost(sourceNode, edge.getCost());
            }
        }

        return new Graph(nodeByLabel.values());
    }

    private static class Edge {
        private final String source;
        private final String target;
        private final int cost;

        private Edge(final String source, final String target, final int cost) {
            this.source = source;
            this.target = target;
            this.cost = cost;
        }

        private String getTarget() {
            return target;
        }

        private String getSource() {
            return source;
        }

        private int getCost() {
            return cost;
        }
    }


}
