package com.prateekj.infrastructure;

import com.prateekj.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration({"classpath:/configuration/test-services-config.xml"})
public class TestSetup {

  @Autowired
  protected UserRepository userRepository;


  protected void clearAllData(){
    userRepository.deleteAll();
  }

}
