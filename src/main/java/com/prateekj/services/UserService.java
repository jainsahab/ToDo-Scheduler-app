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

  public User getUserById(Integer userId) {
    return userRepository.findById(userId);
  }

  public User getUserWithTasks(Integer userId) {
    User foundUser = getUserById(userId);
    foundUser.fetchLazyCollections();
    return foundUser;
  }
}
