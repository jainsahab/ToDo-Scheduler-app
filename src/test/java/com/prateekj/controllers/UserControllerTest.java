package com.prateekj.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prateekj.infrastructure.TestSetup;
import com.prateekj.maker.TaskMaker;
import com.prateekj.maker.UserMaker;
import com.prateekj.model.Task;
import com.prateekj.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends TestSetup{

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;


  @Before
  public void setUp(){
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void shouldAddTheUser() throws Exception {
    User user = make(a(UserMaker.User, with(UserMaker.name, "some-user")));

    mockMvc.perform(put("/users/add").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(status().isCreated());
  }

  @Test
  public void shouldGetTheUserById() throws Exception {
    User user = make(a(UserMaker.User, with(UserMaker.name, "some-user")));
    user = userRepository.save(user);

    mockMvc.perform(get("/users/get").param("userId",user.getId().toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(user.getName()))
        .andExpect(jsonPath("$.id").value(user.getId()));

  }

  @Test
  public void shouldGetTheUserWithTasks() throws Exception {
    User user = make(a(UserMaker.User, with(UserMaker.name, "some-user")));
    user = userRepository.save(user);
    Task aTask = make(a(TaskMaker.Task, with(TaskMaker.user, user)));
    taskRepository.save(aTask);

    mockMvc.perform(get("/users/with-tasks").param("userId",user.getId().toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(user.getName()))
        .andExpect(jsonPath("$.id").value(user.getId()));

  }

  @After
  public void tearDown() throws Exception {
    clearAllData();
  }
}
