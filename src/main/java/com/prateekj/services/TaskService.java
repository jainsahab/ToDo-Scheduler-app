package com.prateekj.services;

import com.prateekj.model.Person;
import com.prateekj.model.Task;
import com.prateekj.repositories.PersonRepository;
import com.prateekj.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private TaskRepository taskRepository;

  public Task saveTaskFor(Person person, Task task) {
    Person existsPerson = personRepository.findOne(person.getId());
    task.setPerson(existsPerson);
    return taskRepository.save(task);
  }
}
