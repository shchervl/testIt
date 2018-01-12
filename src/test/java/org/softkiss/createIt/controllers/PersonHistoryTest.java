package org.softkiss.createIt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.softkiss.createIt.entities.Person;
import org.softkiss.createIt.entities.PersonHistory;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonHistoryTest extends BaseTest {

    private String location;
    private PersonHistory personHistory;

    @Test
    public void createPerson() throws Exception {
        personHistory = new PersonHistory();
        Person person = new Person();
        person.setAge((short) 30);
        person.setDetailedDescription("detailed description");
        person.setName("Jack Nicholson");
        person.setProfession("qa");

        personHistory.setPerson(person);
        personHistory.setSomefield("sdfadfsadfsd");

        location = getMockMvc().perform(
                post("/personHistory")
                        .contentType(getContentType())
                        .content(new ObjectMapper().writeValueAsString(personHistory)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");
    }

    @Test(dependsOnMethods = "createPerson")
    public void updatePerson() throws Exception {
        Person person = new Person();
        person.setAge((short) 30);
        person.setDetailedDescription("detailed description");
        person.setName("Jack Nicholson");
        person.setProfession("qa");
        personHistory.setPerson(person);
        getMockMvc().perform(
                patch(location)
                        .contentType(getContentType())
                        .content(new ObjectMapper().writeValueAsString(personHistory)))
                .andExpect(status().isNoContent());

        getMockMvc().perform(
                get(
                        location)
                        .contentType(getContentType()));
    }
}
