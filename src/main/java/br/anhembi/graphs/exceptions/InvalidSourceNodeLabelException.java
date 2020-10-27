package br.anhembi.graphs.exceptions;

public class InvalidSourceNodeLabelException extends InvalidGraphException {

    public InvalidSourceNodeLabelException(final String label) {
        super(String.format("Source label [%s] is not declared in graph", label));
    }
}
