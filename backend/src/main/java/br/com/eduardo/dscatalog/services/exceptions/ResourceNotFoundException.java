package br.com.eduardo.dscatalog.services.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 971878933695303535L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
