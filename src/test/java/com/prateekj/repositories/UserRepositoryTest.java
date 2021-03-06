package com.prateekj.repositories;

import com.prateekj.infrastructure.TestSetup;
import com.prateekj.maker.UserMaker;
import com.prateekj.model.Task;
import com.prateekj.model.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static com.prateekj.maker.UserMaker.User;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTest extends TestSetup{


  @Test
  public void shouldSaveAUserWithHisTasks(){
    String name = "Prateek Jain";
    String userName = "prateek";
    User prateek = make(a(User,
        with(UserMaker.name, name),
        with(UserMaker.userName, userName)));
    prateek.setName(name);

    Task task1 = new Task();
    task1.setUser(prateek);
    task1.setWork("Work 1");

    Task task2 = new Task();
    task2.setUser(prateek);
    task2.setWork("Work 2");

    prateek.setTasks(asList(task1, task2));

    User savedUser = userRepository.save(prateek);

    User prateekFound = userRepository.findByUserName(savedUser.getUserName());

    assertThat(prateekFound.getName(), is(name));
  }

  @After
  public void tearDown() throws Exception {
    clearAllData();
  }
}
