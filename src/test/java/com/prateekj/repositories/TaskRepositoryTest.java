package com.prateekj.repositories;

import com.prateekj.model.Person;
import com.prateekj.model.Task;
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
@ContextConfiguration("classpath:Beans.xml")
public class TaskRepositoryTest {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private PersonRepository personRepository;

  private Person person;

  @Before
  public void setUp(){
    person = new Person();
    person.setName("some-name");
    personRepository.save(person);
  }

  @After
  public void tearDown(){
    personRepository.deleteAll();
  }

  @Test
  public void shouldSaveTheTaskWhenPersonIsPreSaved(){
    Task someTask = new Task();
    someTask.setWork("some work");
    someTask.setPerson(person);

    Task someTask2 = new Task();
    someTask2.setWork("some work");
    someTask2.setPerson(person);

    taskRepository.save(asList(someTask, someTask2));

    List<Task> allTasks = taskRepository.findByPerson(person);

    assertThat(allTasks, hasSize(2));
    assertThat(allTasks, contains(someTask, someTask2));
  }
}
