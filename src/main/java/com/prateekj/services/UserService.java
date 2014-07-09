package com.prateekj.services;

import com.prateekj.model.User;
import com.prateekj.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public User getUserByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  public User getUserWithTasks(String userName) {
    User foundUser = getUserByUserName(userName);
    foundUser.fetchLazyCollections();
    return foundUser;
  }
}
