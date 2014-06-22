package com.prateekj.repositories;

import com.prateekj.model.Task;
import com.prateekj.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configuration/services-config.xml")
public class TaskRepositoryTest {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private UserRepository userRepository;

  private User user;

  @Before
  public void setUp(){
    user = new User();
    user.setName("some-name");
    userRepository.save(user);
  }

  @After
  public void tearDown(){
    userRepository.deleteAll();
  }

  @Test
  public void shouldSaveTheTaskWhenUserIsPreSaved(){
    Task someTask = new Task();
    someTask.setWork("some work");
    someTask.setUser(user);

    Task someTask2 = new Task();
    someTask2.setWork("some work");
    someTask2.setUser(user);

    taskRepository.save(asList(someTask, someTask2));

    List<Task> allTasks = taskRepository.findByUser(user);

    assertThat(allTasks, hasSize(2));
    assertThat(allTasks, contains(someTask, someTask2));
  }
}
