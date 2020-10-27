//Engenharia da Computação - 9º semestre
//Integrantes do grupo:
//Ana Paula dos Santos Souza - RA: 20894639
//Alexandre Ban de Lima - RA:20878290
//Igor Castro Santilli - RA: 20909361
//Daniel Dasaev Garcia Silva - RA: 20966723
//Leonardo Matheus Silva Balbino - RA: 20613520

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
            System.out.println("Arquivo não encontrado");
        } catch (LabelDoesNotExistException e) {
            System.out.println("Nome do nó não declarado");
        } catch (InvalidSourceNodeLabelException e) {
            System.out.println("Nó de origem invalido");
        } catch (IOException e) {
            System.out.println("Error");
        } catch (IllegalArgumentException e) {
            System.out.println("Argumento invalido");
        }
    }
}
