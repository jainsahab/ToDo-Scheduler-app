package com.prateekj.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prateekj.maker.PersonMaker;
import com.prateekj.model.Person;
import com.prateekj.services.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/configuration/test-services-config.xml", "classpath:configuration/mvc-dispatcher-config.xml"})
public class PersonControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Autowired
  private PersonService personService;

  private Person person;


  @Before
  public void setUp(){
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void shouldAddTheUser() throws Exception {
    person = make(a(PersonMaker.Person, with(PersonMaker.name, "some-person")));

    mockMvc.perform(put("/users/add").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(person)))
        .andExpect(status().isCreated());

    verify(personService).savePerson(person);
  }

}
