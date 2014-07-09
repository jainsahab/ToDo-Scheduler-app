package com.prateekj.controllers;

import com.prateekj.model.User;
import com.prateekj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
@RequestMapping("/users")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService){
    this.userService = userService;
  }

  @RequestMapping(value = "/add", method = PUT)
  public ResponseEntity<Void> addUser(@RequestBody User user){
    userService.saveUser(user);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  @RequestMapping(value = "/get", method = GET)
  public ResponseEntity<User> getUser(@RequestParam String userName){
    User foundUser = userService.getUserByUserName(userName);
    return new ResponseEntity<User>(foundUser, HttpStatus.OK);
  }

  @RequestMapping(value = "/with-tasks", method = RequestMethod.GET)
  public ResponseEntity<User> getUserWithTasks(@RequestParam String userName){
    User foundUser = userService.getUserWithTasks(userName);
    return new ResponseEntity<User>(foundUser, HttpStatus.OK);
  }
}
