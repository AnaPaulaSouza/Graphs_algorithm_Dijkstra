package br.anhembi.graphs.app;


import br.anhembi.graphs.exceptions.InvalidSourceNodeLabelException;
import br.anhembi.graphs.exceptions.LabelDoesNotExistException;
import br.anhembi.graphs.model.Graph;
import br.anhembi.graphs.model.GraphBuilder;
import br.anhembi.graphs.model.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class GraphInputReader {

    private final InputStreamReader inputStreamReader;
    private Graph graph;
    private Node sourceNode;

    public GraphInputReader(final InputStreamReader inputStreamReader) {
        this.inputStreamReader = inputStreamReader;
    }

    public void execute() throws IOException, LabelDoesNotExistException, InvalidSourceNodeLabelException {
        final GraphBuilder graphBuilder = new GraphBuilder();
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        graphBuilder.withIsDirected(Integer.parseInt(bufferedReader.readLine()) == 1);

        int numNodes = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < numNodes; i++) {
            graphBuilder.withNodeLabel(bufferedReader.readLine());
        }

        String line = bufferedReader.readLine();

        String sourceNodeLabel = null;

        while (line != null) {
            final String edgeLine = line;

            line = bufferedReader.readLine();
            if (line == null) {
                sourceNodeLabel = edgeLine;
            } else {
                final String[] split = edgeLine.split(",");
                final Integer cost = (split.length == 3) ? Integer.parseInt(split[2]) : null;
                graphBuilder.withEdge(split[0], split[1], cost);
            }
        }

        graph = graphBuilder.build();
        sourceNode = findNodeByLabel(graph, sourceNodeLabel);
    }

    public Graph getGraph() {
        return graph;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    private Node findNodeByLabel(final Graph graph, final String nodeLabel) throws InvalidSourceNodeLabelException {
        final Optional<Node> nodeOptional = graph.getNodes()
                .stream()
                .filter(node -> node.getLabel().equals(nodeLabel))
                .findFirst();

        if (!nodeOptional.isPresent()) {
            throw new InvalidSourceNodeLabelException(nodeLabel);
        }

        return nodeOptional.get();
    }

}
