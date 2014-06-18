package com.prateekj.services;

import com.prateekj.maker.PersonMaker;
import com.prateekj.maker.TaskMaker;
import com.prateekj.model.Person;
import com.prateekj.model.Task;
import com.prateekj.repositories.PersonRepository;
import com.prateekj.repositories.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

  @InjectMocks
  private TaskService taskService;

  @Mock
  private TaskRepository taskRepository;

  @Mock
  private PersonRepository personRepository;

  @Test
  public void shouldSaveTheBookByAssigningThePersonInIt(){
    Person aPerson = make(a(PersonMaker.Person));
    Task aTask = make(a(TaskMaker.Task));

    taskService.saveTaskFor(aPerson, aTask);

    when(personRepository.findOne(aPerson.getId())).thenReturn(aPerson);
    verify(taskRepository, times(1)).save(eq(aTask));
  }
}
