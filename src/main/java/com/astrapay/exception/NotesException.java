package com.astrapay.exception;

public class NotesException extends RuntimeException {
    public NotesException(String id) {
        super("Note with id " + id + " not found");
    }
}
