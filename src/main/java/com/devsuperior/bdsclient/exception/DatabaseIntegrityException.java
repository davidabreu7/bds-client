package com.devsuperior.bdsclient.exception;

public class DatabaseIntegrityException extends RuntimeException {
    public DatabaseIntegrityException(String database_error) {
        super(database_error);
    }
}
