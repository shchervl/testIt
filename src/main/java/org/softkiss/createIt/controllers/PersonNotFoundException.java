package org.softkiss.createIt.controllers;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String personId) {
        super("could not find person '" + personId + "'.");
    }
}
