package com.jcgfdev.deliveryapp.base.exceptions;

public class NotEntityFound extends RuntimeException {
    private static final String DESCRIPTION = "No existe la entidad buscada";

    public NotEntityFound(String message) {
        super(String.format("%s. %s", DESCRIPTION, message));
    }
}
