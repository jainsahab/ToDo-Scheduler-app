package com.prateekj.maker;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import com.prateekj.model.Task;
import com.prateekj.model.User;

public class TaskMaker {
  public static final Property<Task, Integer> id = new Property();
  public static final Property<Task, String> work = new Property();
  public static final Property<Task, User> user = new Property();


  public static final Instantiator<Task> Task = new Instantiator<Task>() {
    public Task instantiate(PropertyLookup<Task> lookup) {
      Task task = new Task();
      task.setId(lookup.valueOf(id, 1));
      task.setWork(lookup.valueOf(work, "some-work"));
      task.setUser(lookup.valueOf(user, (User) null));
      return task;
    }
  };

}
