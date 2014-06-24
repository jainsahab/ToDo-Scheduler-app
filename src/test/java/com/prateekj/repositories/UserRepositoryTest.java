package com.prateekj.repositories;

import com.prateekj.model.Task;
import com.prateekj.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configuration/services-config.xml")
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Before
  public void setUp(){
    userRepository.deleteAll();
  }

  @Test
  public void shouldSaveAUserWithHisTasks(){
    User prateek = new User();
    String userName = "Prateek";
    prateek.setName(userName);

    Task task1 = new Task();
    task1.setUser(prateek);
    task1.setWork("Work 1");

    Task task2 = new Task();
    task2.setUser(prateek);
    task2.setWork("Work 2");

    prateek.setTasks(asList(task1, task2));

    User savedUser = userRepository.save(prateek);

    User prateekFound = userRepository.findById(savedUser.getId());

    assertThat(prateekFound.getTasks(), hasSize(2));
    assertThat(prateekFound.getTasks(), contains(task1, task2));
    assertThat(prateekFound.getName(), is(userName));
  }
}
