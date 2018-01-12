package org.softkiss.createIt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.softkiss.createIt.entities.Person;
import org.softkiss.createIt.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonsControllerBaseTest extends BaseTest {

    @Autowired
    private PersonRepository personRepository;

    private String location;
    private Person person;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        person = new Person();
        person.setAge((short) 30);
        person.setDetailedDescription("detailed description");
        person.setName("Jack Nicholson");
        person.setProfession("qa");
        personRepository.deleteAll();
    }

    @Test
    public void personNotFound() throws Exception {
        getMockMvc().perform(
                get("/person/123354/")
                        .contentType(getContentType()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createPerson() throws Exception {
        location = getMockMvc().perform(
                post("/people")
                        .contentType(getContentType())
                        .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");
    }

    @Test
    public void tryToCreatePersonWithoutName() throws Exception {
        Person invalidPerson = new Person();
        invalidPerson.setProfession("profession");
        invalidPerson.setAge((short) 30);
        invalidPerson.setDetailedDescription("description");
        getMockMvc().perform(
                post("/people")
                        .contentType(getContentType())
                        .content(new ObjectMapper().writeValueAsString(invalidPerson)))
                .andExpect(status().isPartialContent());
    }

    @Test(dependsOnMethods = "createPerson")
    public void getPerson() throws Exception {
        getMockMvc().perform(
                get(location)
                        .contentType(getContentType()))
                .andExpect(status().isOk());
    }

    @Test(dependsOnMethods = "getPerson")
    public void updatePerson() throws Exception {
        person.setProfession("VP");
        getMockMvc().perform(
                patch(location)
                        .contentType(getContentType())
                        .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(status().isNoContent());

        getMockMvc().perform(
                get(
                        location)
                        .contentType(getContentType()));
    }

    @Test(dependsOnMethods = "updatePerson")
    public void deletePerson() throws Exception {
        getMockMvc().perform(
                delete(location)
                        .contentType(getContentType())
        )
                .andExpect(status().isNoContent());
    }

}
