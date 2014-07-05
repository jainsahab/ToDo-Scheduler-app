package com.prateekj.infrastructure;

import com.prateekj.repositories.TaskRepository;
import com.prateekj.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration({"classpath:/configuration/services-config.xml", "classpath:/configuration/mvc-dispatcher-config.xml"})
public class TestSetup {

  @Autowired
  protected UserRepository userRepository;

  @Autowired
  protected TaskRepository taskRepository;


  protected void clearAllData(){
    taskRepository.deleteAll();
    userRepository.deleteAll();

  }

}
