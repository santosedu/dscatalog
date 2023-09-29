package br.com.eduardo.dscatalog.services.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 971878933695303535L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
