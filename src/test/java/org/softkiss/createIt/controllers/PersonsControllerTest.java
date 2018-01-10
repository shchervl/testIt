package org.softkiss.createIt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.softkiss.Application;
import org.softkiss.createIt.PersonRepository;
import org.softkiss.createIt.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class PersonsControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PersonRepository personRepository;

    private MockMvc mockMvc;
    private String location;
    private Person person;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    @BeforeClass
    public void beforeClass() {
        person = new Person();
        person.setAge((short) 30);
        person.setDetailedDescription("detailed description");
        person.setName("Jack Nicholson");
        person.setProfession("qa");

        personRepository.deleteAll();
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void personNotFound() throws Exception {
        mockMvc.perform(
                get("/person/123354/")
                        .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createPerson() throws Exception {
        location = mockMvc.perform(
                post("/people")
                        .contentType(contentType)
                        .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");
    }

    @Test(dependsOnMethods = "createPerson")
    public void getPerson() throws Exception {
        mockMvc.perform(
                get(location)
                        .contentType(contentType))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.version").value("0"))
 ;
    }

    @Test(dependsOnMethods = "getPerson")
    public void updatePerson() throws Exception {
        person.setProfession("VP");
        mockMvc.perform(
                patch(location)
                        .contentType(contentType)
                        .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(status().isNoContent());

        mockMvc.perform(
                get(
                        "/people/" + person.getId())
                        .contentType(contentType))
//                .andExpect(jsonPath("$.version").value("1"))
        ;
    }

    @Test(dependsOnMethods = "updatePerson")
    public void deletePerson() throws Exception {
        mockMvc.perform(
                delete(location)
                        .contentType(contentType)
        )
                .andExpect(status().isNoContent());
    }

}
