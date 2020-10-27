package br.anhembi.graphs.app;

import br.anhembi.graphs.algorithm.Dijkstra;
import br.anhembi.graphs.exceptions.InvalidSourceNodeLabelException;
import br.anhembi.graphs.exceptions.LabelDoesNotExistException;
import br.anhembi.graphs.model.Graph;
import br.anhembi.graphs.model.Node;

import java.io.*;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        try {
            final InputStream inputStream = App.class.getClassLoader().getResourceAsStream(args[0]);
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            final GraphInputReader graphInputReader = new GraphInputReader(inputStreamReader);
            graphInputReader.execute();

            final Node sourceNode = graphInputReader.getSourceNode();
            final Graph graph = graphInputReader.getGraph();

            final Dijkstra dijkstra = new Dijkstra(graph); 


            final Map<Node, Integer> distances = dijkstra.execute(sourceNode);

            for(Map.Entry<Node, Integer> distanceByNode : distances.entrySet()) {
                System.out.format("Distância ate o vértice %s: %d\n", distanceByNode.getKey().getLabel(), distanceByNode.getValue());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LabelDoesNotExistException e) {
            e.printStackTrace();
        } catch (InvalidSourceNodeLabelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
