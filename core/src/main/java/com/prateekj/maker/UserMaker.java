package com.prateekj.maker;


import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import com.prateekj.model.Task;
import com.prateekj.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMaker {
  public static final Property<User, Integer> id = new Property();
  public static final Property<User, String> name = new Property();
  public static final Property<User, List<Task>> tasks = new Property();


  public static final Instantiator<User> User = new Instantiator<User>() {
    public User instantiate(PropertyLookup<User> lookup) {
      User user = new User();
      user.setId(lookup.valueOf(id, 1));
      user.setName(lookup.valueOf(name, "Ramu Kaka"));
      user.setTasks(lookup.valueOf(tasks, new ArrayList<Task>()));
      return user;
    }
  };

}
