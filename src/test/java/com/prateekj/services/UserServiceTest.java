package com.prateekj.services;

import com.prateekj.model.Task;
import com.prateekj.model.User;
import com.prateekj.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  public void shouldSaveAUserWithItsBook(){
    User someUser = new User();
    someUser.setTasks(asList(new Task()));

    userService.saveUser(someUser);

    verify(userRepository).save(eq(someUser));
  }
}
