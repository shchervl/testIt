package org.softkiss.createIt.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PersonHistory {

    @Id
    private String id;

    private String somefield;

    private Person person;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getSomefield() {
        return somefield;
    }

    public void setSomefield(String somefield) {
        this.somefield = somefield;
    }
}
