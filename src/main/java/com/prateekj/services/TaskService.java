package com.prateekj.services;

import com.prateekj.model.Task;
import com.prateekj.model.User;
import com.prateekj.repositories.TaskRepository;
import com.prateekj.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TaskRepository taskRepository;

  public Task saveTaskFor(User user, Task task) {
    User existsUser = userRepository.findOne(user.getId());
    task.setUser(existsUser);
    return taskRepository.save(task);
  }
}
