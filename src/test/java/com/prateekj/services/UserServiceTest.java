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
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static com.prateekj.maker.UserMaker.User;
import static com.prateekj.maker.UserMaker.name;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
    String userName = "userName";
    User someUser = make(a(User,
        with(name, ramu),
        with(UserMaker.userName, userName)));

    when(userRepository.findByUserName(someUser.getUserName())).thenReturn(someUser);
    User foundUser= userService.getUserByUserName(someUser.getUserName());

    assertThat(foundUser, is(someUser));
  }

  @Test
  public void shouldGetTheUserWithItsAndWithItsLazyCollection(){
    String ramu = "ramu";
    User mockUser = mock(User.class);
    mockUser.setName(ramu);

    when(userRepository.findByUserName(mockUser.getUserName())).thenReturn(mockUser);
    User foundUser= userService.getUserWithTasks(mockUser.getUserName());

    verify(mockUser, times(1)).fetchLazyCollections();
    assertThat(foundUser, is(mockUser));
  }
}
