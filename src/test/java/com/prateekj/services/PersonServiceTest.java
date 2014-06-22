package com.prateekj.services;

import com.prateekj.model.Person;
import com.prateekj.model.Task;
import com.prateekj.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;

  @Test
  public void shouldSaveAPersonWithItsBook(){
    Person somePerson = new Person();
    somePerson.setTasks(asList(new Task()));

    personService.savePerson(somePerson);

    verify(personRepository).save(eq(somePerson));
  }
}
