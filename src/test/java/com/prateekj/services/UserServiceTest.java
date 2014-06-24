package com.prateekj.services;

import com.prateekj.maker.UserMaker;
import com.prateekj.model.Task;
import com.prateekj.model.User;
import com.prateekj.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

  @Test
  public void shouldGetTheUserWithItsName(){
    String ramu = "ramu";
    User someUser = make(a(UserMaker.User));
    someUser.setName(ramu);

    when(userRepository.findById(someUser.getId())).thenReturn(someUser);
    User foundUser= userService.getUserById(someUser.getId());

    assertThat(foundUser, is(someUser));
  }
}
