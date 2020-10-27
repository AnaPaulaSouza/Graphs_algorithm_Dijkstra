package br.anhembi.graphs.algorithm;

import br.anhembi.graphs.model.Graph;
import br.anhembi.graphs.model.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {

    final private Graph graph;

    public Dijkstra(final Graph graph) {
        this.graph = graph;
    }

    public Map<Node, Integer> execute(final Node source) {
        final Map<Node, Integer> distances = new HashMap<>();
        final Map<Node, Node> previousNode = new HashMap<>();

        final PriorityQueue<Node> queue = new PriorityQueue(Comparator.comparingInt(distances::get));
        final Set<Node> consumedNodes = new HashSet<>();

        distances.put(source, 0);
        queue.offer(source);

        while (!queue.isEmpty()) {
            final Node node = queue.poll();

            if (!consumedNodes.contains(node)) {
                consumedNodes.add(node);

                for (Map.Entry<Node, Integer> nodeWithCost : node.getAdjacentNodesWithCost().entrySet()) {
                    final int distance = distances.get(node) + nodeWithCost.getValue();
                    final Node adjacentNode = nodeWithCost.getKey();

                    if (distances.get(adjacentNode) == null ||
                            distance < distances.get(adjacentNode)) {
                        distances.put(adjacentNode, distance);
                        previousNode.put(adjacentNode, node);
                        queue.offer(adjacentNode);
                    }
                }
            }
        }

        return distances;
    }
}
