package com.prateekj.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prateekj.maker.UserMaker;
import com.prateekj.model.User;
import com.prateekj.services.UserService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/configuration/test-services-config.xml", "classpath:configuration/mvc-dispatcher-config.xml"})
public class UserControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Autowired
  private UserService userService;

  private User user;


  @Before
  public void setUp(){
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void shouldAddTheUser() throws Exception {
    user = make(a(UserMaker.User, with(UserMaker.name, "some-user")));

    mockMvc.perform(put("/users/add").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(status().isCreated());

    verify(userService).saveUser(user);
  }

  @Test
  public void shouldGetTheUserById() throws Exception {
    user = make(a(UserMaker.User, with(UserMaker.name, "some-user")));

    when(userService.getUserById(user.getId())).thenReturn(user);

    mockMvc.perform(get("/users/get").param("userId",user.getId().toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(user.getName()))
        .andExpect(jsonPath("$.id").value(user.getId()));

    verify(userService).getUserById(user.getId());
  }

}
