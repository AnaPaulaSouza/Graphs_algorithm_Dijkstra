package br.anhembi.graphs.exceptions;

public class LabelDoesNotExistException extends InvalidGraphException {

    public LabelDoesNotExistException(final String label) {
        super(String.format("Label [%s] is not declared in graph", label));
    }
}
