package com.prateekj.maker;


import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import com.prateekj.model.Task;
import com.prateekj.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonMaker {
  public static final Property<Person, Integer> id = new Property();
  public static final Property<Person, String> name = new Property();
  public static final Property<Person, List<Task>> tasks = new Property();


  public static final Instantiator<Person> Person = new Instantiator<Person>() {
    public Person instantiate(PropertyLookup<Person> lookup) {
      Person person = new Person();
      person.setId(lookup.valueOf(id, 1));
      person.setName(lookup.valueOf(name, "Ramu Kaka"));
      person.setTasks(lookup.valueOf(tasks, new ArrayList<Task>()));
      return person;
    }
  };

}
