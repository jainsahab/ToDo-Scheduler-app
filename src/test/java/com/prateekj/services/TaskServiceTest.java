package com.prateekj.services;

import com.prateekj.maker.TaskMaker;
import com.prateekj.maker.UserMaker;
import com.prateekj.model.Task;
import com.prateekj.model.User;
import com.prateekj.repositories.TaskRepository;
import com.prateekj.repositories.UserRepository;
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
  private UserRepository userRepository;

  @Test
  public void shouldSaveTheTaskByAssigningTheUserInIt(){
    User aUser = make(a(UserMaker.User));
    Task aTask = make(a(TaskMaker.Task));

    when(userRepository.findOne(aUser.getId())).thenReturn(aUser);

    taskService.saveTaskFor(aUser, aTask);
    verify(taskRepository, times(1)).save(eq(aTask));
  }
}
