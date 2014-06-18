package com.prateekj.repositories;

import com.prateekj.model.Person;
import com.prateekj.model.Task;
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
@ContextConfiguration("classpath:Beans.xml")
public class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;

  @Before
  public void setUp(){
    personRepository.deleteAll();
  }

  @Test
  public void shouldSaveAPersonWithHisTasks(){
    Person prateek = new Person();
    String personName = "Prateek";
    prateek.setName(personName);

    Task task1 = new Task();
    task1.setPerson(prateek);
    task1.setWork("Work 1");

    Task task2 = new Task();
    task2.setPerson(prateek);
    task2.setWork("Work 2");

    prateek.setTasks(asList(task1, task2));

    personRepository.save(prateek);

    Person prateekFound = personRepository.findByName(personName);

    assertThat(prateekFound.getTasks(), hasSize(2));
    assertThat(prateekFound.getTasks(), contains(task1, task2));
    assertThat(prateekFound.getName(), is(personName));
  }
}
